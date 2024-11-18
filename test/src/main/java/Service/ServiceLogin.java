package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceLogin {
	
	
	
	
	
	// Configurazione del database
    private static final String DB_URL = "jdbc:mysql://localhost:3306/agenzia";
    private static final String DB_USER = "root"; // Cambia con il tuo username
    private static final String DB_PASSWORD = "Torino2028!"; // Cambia con la tua password

    // Metodo per verificare le credenziali
    public static boolean authenticate(String email, String password) {
        String query = "SELECT COUNT(*) FROM clients WHERE email = ? AND password = ?";
        boolean authentification=false;
        try { 
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query) ;

            // Imposta i valori dei parametri della query
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Esegue la query
            ResultSet resultSet = preparedStatement.executeQuery();
             int count =0;
            
            // Controlla se esiste una corrispondenza
            if (resultSet.next()) {
                count= resultSet.getInt(1) ;
                System.out.println(count);// Se il risultato è maggiore di 0, le credenziali sono valide
                authentification=true;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log dell'errore per il debug
        }

        return authentification; // Credenziali non valide o errore
    }

}
