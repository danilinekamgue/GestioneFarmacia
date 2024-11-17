package controller;

import model.FarmacoModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // PUT THE LOGIC FOR THE LOGIN


        // IF LOGIN IS GOOD, REDIRECT TO ADMIN PAGE OR CLIENT PAGE
        System.out.println("Home controlllll");
        // request.getRequestDispatcher("/jsp/admin.jsp").forward(request, response);

        // this change the url
        response.sendRedirect(request.getContextPath() + "/jsp/client.jsp");
       // response.sendRedirect(request.getContextPath() + "/jsp/admin.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }
}
