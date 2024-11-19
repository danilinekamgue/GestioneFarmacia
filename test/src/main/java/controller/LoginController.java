package controller;

import model.FarmacoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = "cliente";

        HttpSession session = request.getSession();

        session.setAttribute("isLogged", true);
        session.setAttribute("role", role);
        session.setAttribute("email", email);

        // PUT THE LOGIC FOR THE LOGIN


        // IF LOGIN IS GOOD, REDIRECT TO ADMIN PAGE OR CLIENT PAGE
        System.out.println("Home controlllll");
        // request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);

        List<FarmacoModel> farmaci = new ArrayList<>();
        farmaci.add(new FarmacoModel(1L, "nooo","ndofhjgjhgjgjdf",2, 2, LocalDate.now()));
        farmaci.add(new FarmacoModel(2L, "nooo","ndojhgjhjhgjfdf",2, 2, LocalDate.now()));

        System.out.println("je suis cicici " + farmaci.size());
        //request.setAttribute("farmaci", farmaci);
        // this change the url
        // HttpSession session = request.getSession();
        session.setAttribute("farmaci", farmaci);
        response.sendRedirect(request.getContextPath() + "/client/client.jsp");
       // response.sendRedirect(request.getContextPath() + "/jsp/admin.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
