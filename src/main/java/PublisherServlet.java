import Model.Publisher;
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
                JSONObject pub = new JSONObject(jsonArray.get(i).toString());
                JSONObject name = pub.getJSONObject("name");    //Set Author
                JSONObject publisher = pub.getJSONObject("address");   //Set publisher
                temp.setId(Integer.parseInt(pub.get("id").toString()));   //Set ID
                temp.setName(pub.get("name").toString());
                temp.setAddress(pub.get("address").toString());

                publisherList.add(temp);
            }
            request.setAttribute("publisherList", publisherList); //Hàm đẩy bookList qua jsp đây này
        }

        RequestDispatcher rd = request.getRequestDispatcher("Publisher.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
