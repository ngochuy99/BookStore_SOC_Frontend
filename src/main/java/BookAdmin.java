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

@WebServlet(name = "BookAdmin", value = "/BookAdmin")
public class BookAdmin extends HttpServlet {
    private String base_uri = "https://bookstore-restapi-nodejs.herokuapp.com/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Send http request
        URL url = new URL(base_uri + "book");
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
            JSONArray jsonArray = new JSONArray(res.get("books").toString());
            List<com.company.Book> bookList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                com.company.Book temp = new com.company.Book();
                JSONObject book = new JSONObject(jsonArray.get(i).toString());
                JSONObject author = book.getJSONObject("Author");    //Set Author
                JSONObject publisher = book.getJSONObject("Publisher");   //Set publisher
                JSONArray categories = book.getJSONArray("Categories");
                String[] category_temp = new String[categories.length()];
                temp.setAuthor(author.getString("firstname") + author.getString("lastname"));
                temp.setId(Integer.parseInt(book.get("id").toString()));   //Set ID
                temp.setName(book.get("name").toString());
                temp.setDescription(book.get("description").toString());
                temp.setPublisher(publisher.get("name").toString());
                temp.setPrice(Double.parseDouble(book.get("price").toString()));
                temp.setImage(book.getString("Image"));
                for (int k = 0; k < categories.length(); k++) {
                    JSONObject temp2 = categories.getJSONObject(k);
                    category_temp[k] = temp2.get("name").toString();
                }
                temp.setCategory(category_temp);    //Set category
                bookList.add(temp);
            }
            request.setAttribute("booklist1", bookList);
        }
        RequestDispatcher rd = request.getRequestDispatcher("BookManagement.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
