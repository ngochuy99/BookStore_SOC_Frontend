import Model.Publisher;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import Model.Author;

@WebServlet(name = "Publisher", value = "/Publisher")
public class PublisherServlet extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Send http request
        URL url = new URL(base_uri + "publisher");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        StringBuffer jsonres = new StringBuffer();
        if (100 <= http.getResponseCode() && http.getResponseCode() <= 399) {   //return success code
            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonres.append(inputLine);          //Read json from response
            }
            in.close();
            JSONObject res = new JSONObject(jsonres.toString());
            JSONArray jsonArray = new JSONArray(res.get("publisher").toString());
            List<Publisher> publisherList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                Publisher temp = new Publisher();
                JSONObject pub = jsonArray.getJSONObject(i);
                temp.setId(pub.getInt("id"));   //Set ID
                temp.setName(pub.getString("name"));
                temp.setAddress(pub.getString("address"));
                publisherList.add(temp);
            }
            request.setAttribute("publisherList", publisherList); //Hàm đẩy bookList qua jsp đây này
        }

        RequestDispatcher rd = request.getRequestDispatcher("Publisher.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(base_uri+"publisher");
        String name = request.getParameter("name");
        String address = request.getParameter("address");


        //Tao json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("address",address);

        //Set data cho http post request
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPost.setEntity(entity); //set json vao http post request
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse jsonres = client.execute(httpPost); //Thuc hien post du lieu len server
        String content = jsonres.getStatusLine().toString();   //du lieu tra ve tu server
        System.out.println(content);
        if(content.equalsIgnoreCase("HTTP/1.1 200 OK")){
            response.sendRedirect(request.getContextPath()+"/Publisher");
        }
        else {
            response.sendRedirect(request.getContextPath()+"/Publisher");
        }
    }
}
