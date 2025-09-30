package persistencia;  

import java.sql.Connection;  
import java.sql.SQLException; 
import java.sql.Statement;  

public class InicializacionTabla {  // Actualización
    private static final String CREATE_TABLE_SQL =  
        "CREATE TABLE IF NOT EXISTS cartelera (" +  
        "  id INT AUTO_INCREMENT PRIMARY KEY," +   
        "  titulo   VARCHAR(150) NOT NULL," +       
        "  director VARCHAR(50) NOT NULL," +      
        "  anio     INT NOT NULL," +               
        "  duracion INT NOT NULL," +             
        "  genero   VARCHAR(50) NOT NULL" +       
        ")";                                        

    public static void realizar() throws SQLException {  // Actualización
        try (Connection c = DatabaseConnection.getConnection(); 
             Statement st = c.createStatement()) {            
            st.executeUpdate(CREATE_TABLE_SQL);  
        } 
    }  
}  
