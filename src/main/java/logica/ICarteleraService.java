package logica; 

import java.util.List;

public interface ICarteleraService {  // Actualización
    void guardar(Cartelera c) throws Exception;
    void actualizar(Cartelera c) throws Exception;
    void eliminar(int id) throws Exception;
    Cartelera buscarPorId(int id) throws Exception;
    List<Cartelera> listar() throws Exception;
    List<Cartelera> listarFiltrado(Cartelera.Genero genero, Integer anioDesde, Integer anioHasta) throws Exception; // Actualizacion

}
