package igu.util;

import static igu.util.FormUtils.formatearGenero;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import logica.Cartelera;

public final class CsvExporter {
    private CsvExporter(){}

    public static void exportarCartelera(File destino, List<Cartelera> filas) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(destino), StandardCharsets.UTF_8))) {

            // Encabezados
            bw.write("ID;Titulo;Director;Año;Duracion;Genero");
            bw.newLine();

            for (Cartelera c : filas) {
                bw.write(String.format("%d;%s;%s;%d;%d;%s",
                        c.getId(),
                        escapar(c.getTitulo()),
                        escapar(c.getDirector()),
                        c.getAnio(),
                        c.getDuracion(),
                        c.getGenero() != null ? formatearGenero(c.getGenero()) : ""
                ));
                bw.newLine();
            }
        }
    }

    private static String escapar(String s) {
        if (s == null) return "";
        // Separador ';'
        return s.replace(";", ",").trim();
    }
}
