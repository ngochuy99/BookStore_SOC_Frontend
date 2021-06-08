

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@WebServlet(name = "addCart", value = "/addCart")
public class addToCart extends HttpServlet {

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
//        request.getSession().setAttribute("cart",list);
//        response.sendRedirect("mainPage");
        RequestDispatcher rd = request.getRequestDispatcher("Order.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Tao connect den server
        doGet(request,response);
    }
}
