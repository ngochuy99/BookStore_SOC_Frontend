import Model.Item;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "order", value = "/order")
public class Order extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Order.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Model.Order order = (Model.Order) session.getAttribute("order");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(base_uri+"order");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("destination", order.getDestination());
        jsonObject.put("shipmentFee", order.getShipmentFee());
        jsonObject.put("userId", 1);
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
        System.out.println(content);
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            session.removeAttribute("order");
            response.sendRedirect(request.getContextPath()+"/mainPage");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/addCart");
        }
    }
}
