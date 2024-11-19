package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Farmaco;

@WebServlet("/farmaci")
public class FarmaciController extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
	  @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Simulation de médicaments
	        List<Farmaco> farmaci = new ArrayList<>();
	        farmaci.add(new Farmaco(1, "Paracetamol", "Antipiretico"));
	        farmaci.add(new Farmaco(2, "Ibuprofene", "Antinfiammatorio"));
	        farmaci.add(new Farmaco(3, "Amoxicillina", "Antibiotico"));
            System.out.println(farmaci);
            System.out.println("nnnn");
	        // Ajouter la liste des médicaments en attribut de requête
	        request.setAttribute("farmaci", farmaci);

	        // Rediriger vers le JSP pour afficher la liste
	        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
	        dispatcher.forward(request, response);
	    }

}
