import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "updateBook", value = "/updateBook")
public class UpdateBook extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("BookManagement.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tao connect den server
        String id = request.getParameter("bookidupdate");
        System.out.println(id);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(base_uri+"book/"+id);
        String name = request.getParameter("booknameupdate");
        String price = request.getParameter("priceupdate");
        String description = request.getParameter("descriptionupdate");
        String author_firstname = request.getParameter("author_firstnameupdate");
        String author_lastname = request.getParameter("author_lastnameupdate");
        String category = request.getParameter("categoryupdate");
        String publisher = request.getParameter("publisherupdate");
        String inStock = request.getParameter("inStockupdate");
        //Tao json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("inStock",Integer.parseInt(inStock));
        jsonObject.put("price",Integer.parseInt(price));
        jsonObject.put("description",description);
        jsonObject.put("author_firstname",author_firstname);
        jsonObject.put("author_lastname",author_lastname);
        JSONArray category_list = new JSONArray();
        category_list.put(0,category);
        jsonObject.put("category_list",category_list);
        jsonObject.put("publisher_name",publisher);
        jsonObject.put("image","");
        //Set data cho http post request
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPut.setEntity(entity); //set json vao http post request
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        CloseableHttpResponse jsonres = client.execute(httpPut); //Thuc hien post du lieu len server
        String content = jsonres.getStatusLine().toString();   //du lieu tra ve tu server
        System.out.println(content);
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            response.sendRedirect(request.getContextPath()+"/BookAdmin");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/BookAdmin");
        }
    }
}
