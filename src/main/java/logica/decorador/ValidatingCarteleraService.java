package logica.decorador;

import java.util.List;
import logica.Cartelera;
import logica.ICarteleraService;

public class ValidatingCarteleraService extends CarteleraServiceDecorator {  // Actualización
    
    public ValidatingCarteleraService(ICarteleraService wrappee) { super(wrappee); }

    private void validarCampos(Cartelera c) {
        if (c == null) throw new IllegalArgumentException("Cartelera nula");
        if (c.getTitulo() == null || c.getTitulo().isBlank()) throw new IllegalArgumentException("Título obligatorio");
        if (c.getDirector() == null || c.getDirector().isBlank()) throw new IllegalArgumentException("Director obligatorio");
        if (c.getAnio() < 1895 || c.getAnio() > 2100) throw new IllegalArgumentException("Año fuera de rango");
        if (c.getDuracion() <= 0) throw new IllegalArgumentException("Duración debe ser positiva");
        if (c.getGenero() == null) throw new IllegalArgumentException("Género obligatorio");
    }

    @Override public void guardar(Cartelera c) throws Exception {
        validarCampos(c);
        super.guardar(c);
    }

    @Override public void actualizar(Cartelera c) throws Exception {
        if (c == null || c.getId() <= 0) throw new IllegalArgumentException("Id inválido");
        validarCampos(c);
        super.actualizar(c);
    }

    @Override public void eliminar(int id) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("Id inválido");
        super.eliminar(id);
    }

    @Override public Cartelera buscarPorId(int id) throws Exception {
        if (id <= 0) throw new IllegalArgumentException("Id inválido");
        return super.buscarPorId(id);
    }

    @Override public List<Cartelera> listar() throws Exception { return super.listar(); }
}
