package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;

public class JpaCarteleraService implements ICarteleraService {  // Actualización

    private final ControladoraPersistencia repo;

    public JpaCarteleraService(ControladoraPersistencia repo) {
        this.repo = repo;
    }

    @Override
    public void guardar(Cartelera c) throws Exception {
        repo.guardar(c);
    }

    @Override
    public void actualizar(Cartelera c) throws Exception {
        repo.actualizar(c);
    }

    @Override
    public void eliminar(int id) throws Exception {
        repo.eliminar(id);
    }

    @Override
    public Cartelera buscarPorId(int id) throws Exception {
        return repo.buscarPorId(id);
    }

    @Override
    public List<Cartelera> listar() throws Exception {
        return repo.listar();
    }
    
    @Override // Actualizacion
    public List<Cartelera> listarFiltrado(Cartelera.Genero genero, Integer anioDesde, Integer anioHasta) throws Exception {
        return repo.listarFiltrado(genero, anioDesde, anioHasta);
    }
}
