
import Model.Book;
import Model.Item;
import Model.Order;
import Model.User;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(name = "addCart", value = "/addCart")
public class addToCart extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        //Send http request
        String id = request.getParameter("id");
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
            order.setDestination(user.getAddress());
            order.setShipmentFee("10000");
            order.setUser(user);
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
        }
        RequestDispatcher rd = request.getRequestDispatcher("Order.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order) session.getAttribute("order");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(base_uri+"order");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("destination", order.getDestination());
        jsonObject.put("shipmentFee", order.getShipmentFee());
        jsonObject.put("userId", order.getUser().getId());
        JSONArray books = new JSONArray();
        Map map = new HashMap();
        for (Item item: order.getItems()){
            map.put("book", item.getBook().getId());
            map.put("quantity", item.getQuantity());
            books.put(map);
        }
        jsonObject.put("books", books);
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPost.setEntity(entity); //set json vao http post request
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse jsonres = client.execute(httpPost); //Thuc hien post du lieu len server
        String content = jsonres.getStatusLine().toString();   //du lieu tra ve tu server
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            session.removeAttribute("order");
            response.sendRedirect(request.getContextPath()+"/mainPage");
        }
        else {
            RequestDispatcher rd = request.getRequestDispatcher("/Order.jsp");
            rd.forward(request, response);
        }
    }
}
