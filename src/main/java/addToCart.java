

import Model.Author;
import Model.Book;
import Model.Item;
import Model.Order;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@WebServlet(name = "addCart", value = "/addCart")
public class addToCart extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Send http request
        String id = request.getParameter("id");
//        ArrayList<String> list = new ArrayList<>();
//        if (request.getSession().getAttribute("cart")==null){
//            request.getSession().setAttribute("cart",list);
//        }
//        list = (ArrayList<String>) request.getSession().getAttribute("cart");
//        list.add(id);
        URL url = new URL(base_uri + "book/" + id);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        StringBuilder jsonres = new StringBuilder();
        BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            jsonres.append(inputLine);          //Read json from response
        }
        in.close();
        JSONObject res = new JSONObject(jsonres.toString());
        JSONObject b = new JSONObject(res.get("book").toString());
        Book book = new Book();
        JSONObject author = b.getJSONObject("Author");    //Set Author
        JSONObject publisher = b.getJSONObject("Publisher");   //Set publisher
        JSONArray categories = b.getJSONArray("Categories");
        String[] category_temp = new String[categories.length()];
        book.setAuthor(author.getString("firstname") + author.getString("lastname"));
        book.setId(Integer.parseInt(b.get("id").toString()));   //Set ID
        book.setName(b.get("name").toString());
        book.setDescription(b.get("description").toString());
        book.setPublisher(publisher.get("name").toString());
        book.setPrice(Double.parseDouble(b.get("price").toString()));
        book.setImage(b.getString("Image"));
        for (int k = 0; k < categories.length(); k++) {
            JSONObject temp2 = categories.getJSONObject(k);
            category_temp[k] = temp2.get("name").toString();
        }
        book.setCategory(category_temp);    //Set category

        int quantity = 1;
        HttpSession session = request.getSession();
        if (session.getAttribute("order" ) == null){
            Order order = new Order();
            List<Item> items = new ArrayList<>();
            items.add(new Item(book, quantity, book.getPrice()));
            order.setItems(items);
            session.setAttribute("order", order);
        } else {
            Order order = (Order) session.getAttribute("order");
            List<Item> items = order.getItems();
            boolean check = false;
            for (Item item: items){
                if (item.getBook().getId() == book.getId()){
                    item.setQuantity(item.getQuantity() + quantity);
                    check = true;
                }
            }
            if (check == false) {
                Item item = new Item(book, quantity, book.getPrice());
                items.add(item);
            }
            session.setAttribute("order", order);
        }
//        request.getSession().setAttribute("cart",list);
        RequestDispatcher rd = request.getRequestDispatcher("Order.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tao connect den server
        doGet(request,response);
    }
}
