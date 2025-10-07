package logica.decorador; 

import java.util.List;
import logica.Cartelera;
import logica.ICarteleraService;

public class LoggingCarteleraService extends CarteleraServiceDecorator {  // Actualización
    
    public LoggingCarteleraService(ICarteleraService wrappee) { super(wrappee); }

    @Override public void guardar(Cartelera c) throws Exception {
        long t0 = System.currentTimeMillis();
        try { super.guardar(c); System.out.println("[Cartelera][JPA] CREATE '" + c.getTitulo() + "' OK " + (System.currentTimeMillis()-t0) + "ms"); }
        catch (Exception ex) { System.err.println("[Cartelera][JPA] CREATE ERROR: " + ex.getMessage()); throw ex; }
    }

    @Override public void actualizar(Cartelera c) throws Exception {
        long t0 = System.currentTimeMillis();
        try { super.actualizar(c); System.out.println("[Cartelera][JPA] UPDATE id=" + c.getId() + " OK " + (System.currentTimeMillis()-t0) + "ms"); }
        catch (Exception ex) { System.err.println("[Cartelera][JPA] UPDATE ERROR: " + ex.getMessage()); throw ex; }
    }

    @Override public void eliminar(int id) throws Exception {
        long t0 = System.currentTimeMillis();
        try { super.eliminar(id); System.out.println("[Cartelera][JPA] DELETE id=" + id + " OK " + (System.currentTimeMillis()-t0) + "ms"); }
        catch (Exception ex) { System.err.println("[Cartelera][JPA] DELETE ERROR: " + ex.getMessage()); throw ex; }
    }
    
    @Override
    public List<Cartelera> listarFiltrado(Cartelera.Genero genero,
            Integer anioDesde,
            Integer anioHasta) throws Exception {
        long t0 = System.currentTimeMillis();
        try {
            List<Cartelera> r = super.listarFiltrado(genero, anioDesde, anioHasta);
            System.out.println("[Cartelera][JPA] LISTAR/FILTRADO genero=" + genero
                    + " desde=" + anioDesde + " hasta=" + anioHasta
                    + " -> " + r.size() + " filas en " + (System.currentTimeMillis() - t0) + "ms");
            return r;
        } catch (Exception ex) {
            System.err.println("[Cartelera][JPA] LISTAR/FILTRADO ERROR: " + ex.getMessage());
            throw ex;
        }
    }
}
