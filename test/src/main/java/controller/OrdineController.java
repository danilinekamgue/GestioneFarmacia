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
import java.util.Map;


@WebServlet(urlPatterns = {"/ordine"})
public class OrdineController   extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> names = request.getParameterMap();

        // CHECK IF I CAN SERVE

        // IF YES I SERVE

        // ELSE I GIVE BACK THE WARNING MESSAGE
        for(String key : names.keySet()){

            System.out.println(key);
            //  System.out.println(names.get(key).toString());
            //
        }

        // this change the url
        response.sendRedirect(request.getContextPath() + "/jsp/cliente-ordine.jsp");
        // response.sendRedirect(request.getContextPath() + "/jsp/admin.jsp");
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<FarmacoModel> farmaci = new ArrayList<>();
        farmaci.add(new FarmacoModel(1L, "nooo","ndofdf",2, 2, LocalDate.now()));
        farmaci.add(new FarmacoModel(2L, "nooo","ndofdf",2, 2, LocalDate.now()));

        System.out.println("je suis cicici " + farmaci.size());
        request.setAttribute("farmaci", farmaci);
        request.setAttribute("tttt", "tttt");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

}
