package logica;

import igu.MainMenu;
import logica.controlador.CarteleraController;
import logica.decorador.LoggingCarteleraService;
import logica.decorador.ValidatingCarteleraService;
import persistencia.ControladoraPersistencia;

public class CineMagenta {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {

            ControladoraPersistencia repo = new ControladoraPersistencia();
            ICarteleraService core = new JpaCarteleraService(repo); // o new JdbcCarteleraService()
            ICarteleraService service = new LoggingCarteleraService(
                    new ValidatingCarteleraService(core)
            );
            CarteleraController controller = new CarteleraController(service);

            // 2) Inyectar el controller en el menú
            MainMenu mainMenu = new MainMenu(controller);
            mainMenu.setVisible(true);
            mainMenu.setLocationRelativeTo(null);
        });
    }
}

