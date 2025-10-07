package igu.util;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import logica.Cartelera;
import logica.validacion.CarteleraValidator;
import logica.validacion.exceptions.ValidationException;

public final class FormUtils {

    private FormUtils() {
    }

    // DTO para devolver todo ya validado/parseado
    public static final class PeliculaInput { // Actualizacion

        public final String titulo;
        public final String director;
        public final int anio;
        public final int duracion;
        public final Cartelera.Genero genero;

        public PeliculaInput(String titulo, String director, int anio, int duracion, Cartelera.Genero genero) {
            this.titulo = titulo;
            this.director = director;
            this.anio = anio;
            this.duracion = duracion;
            this.genero = genero;
        }
    }

    public static PeliculaInput leerYValidarCampos(
            java.awt.Component parent,
            JTextField txtNombre,
            JTextField txtDirector,
            JTextField txtAnio,
            JTextField txtDuracion,
            JComboBox<String> cmbGenero
    ) {
        // Validar campos vacíos
        if (txtNombre.getText().isBlank() || txtDirector.getText().isBlank()
                || txtAnio.getText().isBlank() || txtDuracion.getText().isBlank()) {
            JOptionPane.showMessageDialog(parent, "Todos los campos son obligatorios", "Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        final int anio, duracion;

        // Validar formato numérico de AÑO
        try {
            anio = Integer.parseInt(txtAnio.getText().trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(parent,
                    "El campo año debe ser un número válido",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validar formato numérico de DURACIÓN
        try {
            duracion = Integer.parseInt(txtDuracion.getText().trim());
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(parent,
                    "El campo duración debe ser un número válido",
                    "Error de formato", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validar año y duración usando el validador centralizado
        try {
            CarteleraValidator.validarAnio(anio);
            CarteleraValidator.validarDuracion(duracion);
        } catch (ValidationException ve) {
            JOptionPane.showMessageDialog(parent, ve.getMessage(), "Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validar género
        String generoStr = (String) cmbGenero.getSelectedItem();
        if (generoStr == null || "-".equals(generoStr)) {
            JOptionPane.showMessageDialog(parent, "Debe seleccionar un género válido", "Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        Cartelera.Genero genero;
        try {
            genero = mapGenero(generoStr);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(parent, "Género no válido", "Validación", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Devuelve todo validado
        return new PeliculaInput(
                txtNombre.getText().trim(),
                txtDirector.getText().trim(),
                anio,
                duracion,
                genero
        );
    }

    // Mapeo único Combo → Enum
    public static Cartelera.Genero mapGenero(String generoStr) throws IllegalArgumentException {
        return switch (generoStr) {
            case "Comedia" ->
                Cartelera.Genero.COMEDIA;
            case "Drama" ->
                Cartelera.Genero.DRAMA;
            case "Acción" ->
                Cartelera.Genero.ACCION;
            case "Terror" ->
                Cartelera.Genero.TERROR;
            case "Ciencia Ficción" ->
                Cartelera.Genero.CIENCIA_FICCION;
            case "Aventura" ->
                Cartelera.Genero.AVENTURA;
            default ->
                throw new IllegalArgumentException("Género no válido");
        };
    }

    public static void seleccionarGeneroEnCombo(JComboBox<String> combo, Cartelera.Genero genero) {
        if (genero == null) {
            combo.setSelectedIndex(0);
            return;
        }
        String etiqueta = switch (genero) {
            case COMEDIA ->
                "Comedia";
            case DRAMA ->
                "Drama";
            case ACCION ->
                "Acción";
            case TERROR ->
                "Terror";
            case CIENCIA_FICCION ->
                "Ciencia Ficción";
            case AVENTURA ->
                "Aventura";
        };
        combo.setSelectedItem(etiqueta);
    }

    public static void limpiarCampos(JTextField... fields) {
        for (JTextField f : fields) {
            f.setText("");
        }
    }

    public static String formatearGenero(Cartelera.Genero genero) {
        if (genero == null) {
            return "N/A";
        }
        return switch (genero) {
            case COMEDIA ->
                "Comedia";
            case DRAMA ->
                "Drama";
            case ACCION ->
                "Acción";
            case TERROR ->
                "Terror";
            case CIENCIA_FICCION ->
                "Ciencia Ficción";
            case AVENTURA ->
                "Aventura";
        };
    }

}
