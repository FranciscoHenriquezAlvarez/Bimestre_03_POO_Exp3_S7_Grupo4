package logica;

import igu.MainMenu;
import javax.swing.JOptionPane;
import logica.controlador.CarteleraController;
import logica.decorador.LoggingCarteleraService;
import logica.decorador.ValidatingCarteleraService;
import persistencia.ControladoraPersistencia;

public class CineMagenta {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            
            try {
                // ✅ Verificación inicial de conexión a la base de datos
                javax.persistence.Persistence
                        .createEntityManagerFactory("CineMagentaPU")
                        .createEntityManager()
                        .close();
                JOptionPane.showMessageDialog(null,
                        "Conexión a Cine_DB exitosa.",
                        "Base de Datos",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "❌ No se pudo conectar a la BD:\n" + ex.getMessage(),
                        "Error de Conexión",
                        JOptionPane.ERROR_MESSAGE);
                return; // Detiene la ejecución si no hay conexión
            }
            
            // Inicialización de los servicios
            ControladoraPersistencia repo = new ControladoraPersistencia();
            ICarteleraService core = new JpaCarteleraService(repo); // o new JdbcCarteleraService()
            ICarteleraService service = new LoggingCarteleraService(
                    new ValidatingCarteleraService(core)
            );
            CarteleraController controller = new CarteleraController(service);

            // Mostrar la ventana principal
            MainMenu mainMenu = new MainMenu(controller);
            mainMenu.setVisible(true);
            mainMenu.setLocationRelativeTo(null);
        });
    }
}

