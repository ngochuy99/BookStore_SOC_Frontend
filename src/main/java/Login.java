import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tao connect den server
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(base_uri+"login");
        //Lay du lieu tu file jsp
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //Tao json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username",username);
        jsonObject.put("password",password);
        //Set data cho http post request
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPost.setEntity(entity); //set json vao http post request
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse jsonres = client.execute(httpPost); //Thuc hien post du lieu len server
        String content = IOUtils.toString(jsonres.getEntity().getContent(), "UTF-8");   //du lieu tra ve tu server
        System.out.println(content);
        if(content.isEmpty()){
            response.sendRedirect(request.getContextPath()+"/login");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/BookAdmin");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }
}
