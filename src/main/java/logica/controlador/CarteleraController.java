package logica.controlador;

import java.util.List;
import logica.Cartelera; 
import logica.ICarteleraService; 

public class CarteleraController {  // Actualización

    private final ICarteleraService service;

    public CarteleraController(ICarteleraService service) {
        this.service = service;
    }

    public void onGuardarPelicula(String titulo, String director, int anio, int duracion, Cartelera.Genero genero) throws Exception {
        Cartelera c = new Cartelera();
        c.setTitulo(titulo != null ? titulo.trim() : null);
        c.setDirector(director != null ? director.trim() : null);
        c.setAnio(anio);
        c.setDuracion(duracion);
        c.setGenero(genero);
        service.guardar(c);
    }

    public List<Cartelera> onListar() throws Exception {
        return service.listar();
    }

    public Cartelera onBuscarPorId(int id) throws Exception {
        return service.buscarPorId(id);
    }

    public void onEliminarPelicula(int id) throws Exception {
        service.eliminar(id);
    }

    public void onActualizarPelicula(int id, String titulo, String director, int anio, int duracion, Cartelera.Genero genero) throws Exception {
        Cartelera c = new Cartelera();
        c.setId(id);
        c.setTitulo(titulo != null ? titulo.trim() : null);
        c.setDirector(director != null ? director.trim() : null);
        c.setAnio(anio);
        c.setDuracion(duracion);
        c.setGenero(genero);
        service.actualizar(c);
    }
    
    public List<Cartelera> onBuscarFiltrado(Cartelera.Genero genero, Integer anioDesde, Integer anioHasta) throws Exception { // Actualizacion
        return service.listarFiltrado(genero, anioDesde, anioHasta);
    }
}
