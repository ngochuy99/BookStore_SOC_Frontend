

import Model.Book;
import Model.Item;
import Model.Order;
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
import java.util.List;


@WebServlet(name = "addCart", value = "/addCart")
public class addToCart extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Send http request
        String id = request.getParameter("id");
        ArrayList<String> list = new ArrayList<>();
        if (request.getSession().getAttribute("cart")==null){
            request.getSession().setAttribute("cart",list);
        }
        list = (ArrayList<String>) request.getSession().getAttribute("cart");
        list.add(id);
//        int quantity = 1;
//        Book book = (Book) request.getAttribute("book");
//        HttpSession session = request.getSession();
//        if (session.getAttribute("order" ) == null){
//            Order order = new Order();
//            List<Item> items = new ArrayList<>();
//            items.add(new Item(book, quantity, book.getPrice()));
//            order.setItems(items);
//            session.setAttribute("order", order);
//        } else {
//            Order order = (Order) session.getAttribute("order");
//            List<Item> items = order.getItems();
//            boolean check = false;
//            for (Item item: items){
//                if (item.getBook().getId() == book.getId()){
//                    item.setQuantity(item.getQuantity() + quantity);
//                    check = true;
//                }
//            }
//            if (check == false) {
//                Item item = new Item(book, quantity, book.getPrice());
//                items.add(item);
//            }
//            session.setAttribute("order", order);
//        }
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
