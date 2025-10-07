package igu.util;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JTextField;

public final class ValorPorDefecto {
    private ValorPorDefecto() {}

    private static final Color COLOR_MARCADOR = new Color(150,150,150);
    private static final Color COLOR_TEXTO = new Color(0,0,0);

    public static void aplicar(JTextField field, String placeholder) {
        field.putClientProperty("ph.text", placeholder);

        // Estado inicial
        if (field.getText() == null || field.getText().isBlank() || field.getText().equals(placeholder)) {
            mostrar(field, placeholder);
        } else {
            ocultar(field);
        }

        field.addFocusListener(new FocusAdapter() {
            @Override public void focusGained(FocusEvent e) {
                if (estaMostrando(field)) {
                    field.setText("");
                    ocultar(field);
                }
            }
            @Override public void focusLost(FocusEvent e) {
                if (field.getText().isBlank()) {
                    mostrar(field, placeholder);
                }
            }
        });
    }

    public static void reset(JTextField field) {
        String ph = (String) field.getClientProperty("ph.text");
        if (ph != null) mostrar(field, ph);
    }

    public static boolean estaMostrando(JTextField field) {
        return Boolean.TRUE.equals(field.getClientProperty("ph.showing"));
    }

    private static void mostrar(JTextField f, String ph) {
        f.setForeground(COLOR_MARCADOR);
        f.setText(ph);
        f.putClientProperty("ph.showing", Boolean.TRUE);
    }

    private static void ocultar(JTextField f) {
        f.setForeground(COLOR_TEXTO);
        f.putClientProperty("ph.showing", Boolean.FALSE);
    }
}

