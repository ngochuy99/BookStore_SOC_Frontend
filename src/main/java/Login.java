import Model.User;
import org.apache.commons.io.IOUtils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
        CloseableHttpResponse jsonres = client.execute(httpPost);
        String content = jsonres.getStatusLine().toString();
        JSONObject user = new JSONObject(EntityUtils.toString(jsonres.getEntity())).getJSONObject("user");
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role",user.get("role"));
            response.sendRedirect(request.getContextPath()+"/BookAdmin");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/Login");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.forward(request, response);
    }
}
