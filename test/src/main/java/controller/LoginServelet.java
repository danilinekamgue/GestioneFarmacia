package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.ServiceLogin;


@WebServlet("/LoginServlet")
public class LoginServelet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.sendRedirect("login.jsp");
    }
	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Recupera i parametri dal form di login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Crea un'istanza del servizio per autenticare l'utente
        

        // Verifica le credenziali
        boolean isAuthenticated = ServiceLogin.authenticate(username, password);
        System.out.println(isAuthenticated);

        if (isAuthenticated) {
            // Se autenticato, crea una sessione per l'utente
            HttpSession session = request.getSession();
            session.setAttribute("email", username);

            // Reindirizza alla home page
            response.sendRedirect("home.jsp");
        } else {
            // Se non autenticato, reindirizza al login con un messaggio di errore
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }
}
