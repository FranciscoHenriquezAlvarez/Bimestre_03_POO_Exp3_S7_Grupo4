package persistencia;

import java.util.List;
import logica.Cartelera;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {

    CarteleraJpaController carteleraJpaController = new CarteleraJpaController();

    public void guardar(Cartelera cartelera) {
        /*Creamos en la BD la película*/
        carteleraJpaController.create(cartelera);
    }

    public void actualizar(Cartelera cartelera) { // Actualizacion
        carteleraJpaController.edit(cartelera);
    }

    public void eliminar(int id) throws NonexistentEntityException{
        carteleraJpaController.destroy(id);
    }

    public Cartelera buscarPorId(int id) {
        return carteleraJpaController.findPelicula(id);
    }

    public List<Cartelera> listar() {
        return carteleraJpaController.findCarteleraEntities();
    }
    
    public List<Cartelera> listarFiltrado(Cartelera.Genero genero, Integer anioDesde, Integer anioHasta) { // Actualizacion
        return carteleraJpaController.buscarPorFiltros(genero, anioDesde, anioHasta);
    }

}
