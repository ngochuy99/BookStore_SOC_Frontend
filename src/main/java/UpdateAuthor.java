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

@WebServlet(name = "updateAuthor", value = "/updateAuthor")
public class UpdateAuthor extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Author.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tao connect den server
        String id = request.getParameter("authoridupdate");
        System.out.println(id);
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut(base_uri+"author/"+id);
        String firstname = request.getParameter("firstnameupdate");
        String lastname = request.getParameter("lastnameupdate");
        String dob = request.getParameter("dobupdate");
        //Tao json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstname",firstname);
        jsonObject.put("lastname",lastname);
        jsonObject.put("dob",dob);
        //Set data cho http post request
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPut.setEntity(entity); //set json vao http post request
        httpPut.setHeader("Accept", "application/json");
        httpPut.setHeader("Content-type", "application/json");
        CloseableHttpResponse jsonres = client.execute(httpPut); //Thuc hien post du lieu len server
        String content = jsonres.getStatusLine().toString();   //du lieu tra ve tu server
        System.out.println(content);
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            response.sendRedirect(request.getContextPath()+"/AuthorAdmin");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/AuthorAdmin");
        }
    }
}
