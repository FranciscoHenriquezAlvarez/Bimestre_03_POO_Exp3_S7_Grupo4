package persistencia.opcional;  

import java.sql.Connection;  
import java.sql.SQLException; 
import java.sql.Statement;  

public class InicializacionTabla {  // Implementación alternativa
    private static final String CREATE_TABLE_SQL =  
        "CREATE TABLE IF NOT EXISTS cartelera (" +  
        "  id INT AUTO_INCREMENT PRIMARY KEY," +   
        "  titulo   VARCHAR(150) NOT NULL," +       
        "  director VARCHAR(50) NOT NULL," +      
        "  anio     INT NOT NULL," +               
        "  duracion INT NOT NULL," +             
        "  genero   VARCHAR(50) NOT NULL" +       
        ")";    
    
    private static final String CREATE_INDEX_GENERO_ANIO
            = "CREATE INDEX IF NOT EXISTS idx_cartelera_genero_anio ON cartelera (genero, anio)";
    private static final String CREATE_INDEX_ANIO
            = "CREATE INDEX IF NOT EXISTS idx_cartelera_anio ON cartelera (anio)";
    private static final String CREATE_INDEX_TITULO
            = "CREATE INDEX IF NOT EXISTS idx_cartelera_titulo ON cartelera (titulo)";
    private static final String CREATE_INDEX_DIRECTOR
            = "CREATE INDEX IF NOT EXISTS idx_cartelera_director ON cartelera (director)";

    public static void realizar() throws SQLException {  // Actualización
        try (Connection c = DatabaseConnection.getConnection(); 
             Statement st = c.createStatement()) {            
            st.executeUpdate(CREATE_TABLE_SQL);  
        } 
    }  
}  
