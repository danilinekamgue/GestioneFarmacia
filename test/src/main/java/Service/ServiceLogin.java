package Service;

import config.DbConfig;
import config.DbInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ServiceLogin {
	

    // Metodo per verificare le credenziali
    public static boolean authenticate(String email, String password) {

        // Connexion à la base de données
        DbInfo db = DbConfig.getDbConfig();

        String query = "SELECT COUNT(*) FROM clients WHERE email = ? AND password = ?";
        boolean authentification=false;
        try { 
        	Class.forName("com.mysql.cj.jdbc.Driver");
        	Connection connection = DriverManager.getConnection(db.getUrl(), db.getUser(), db.getPassword());
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
