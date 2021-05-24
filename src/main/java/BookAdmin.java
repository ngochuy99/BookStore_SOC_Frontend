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
        // Tao connect den server
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(base_uri+"books");
        //Lay du lieu tu file jsp
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String price = request.getParameter("price");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String category = request.getParameter("category");
        String publisher = request.getParameter("publisher");
        //Tao json object
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("name",name);
        jsonObject.put("price",price);
        jsonObject.put("description",description);
        jsonObject.put("author",author);
        jsonObject.put("category",category);
        jsonObject.put("publisher",publisher);
        //Set data cho http post request
        StringEntity entity = new StringEntity(jsonObject.toString());
        httpPost.setEntity(entity); //set json vao http post request
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");
        CloseableHttpResponse jsonres = client.execute(httpPost); //Thuc hien post du lieu len server
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
