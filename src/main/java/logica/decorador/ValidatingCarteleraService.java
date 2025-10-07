package logica.decorador;

import java.util.List;
import logica.Cartelera;
import logica.ICarteleraService;
import logica.validacion.CarteleraValidator;
import logica.validacion.exceptions.ValidationException;

public class ValidatingCarteleraService extends CarteleraServiceDecorator {  // Actualización

    public ValidatingCarteleraService(ICarteleraService wrappee) {
        super(wrappee);
    }

    @Override
    public void guardar(Cartelera c) throws ValidationException, Exception {
        CarteleraValidator.validar(c);
        super.guardar(c);
    }

    @Override
    public void actualizar(Cartelera c) throws ValidationException, Exception {
        if (c == null || c.getId() <= 0) {
            throw new ValidationException("Id inválido");
        }
        CarteleraValidator.validar(c);
        super.actualizar(c);
    }

    @Override
    public void eliminar(int id) throws ValidationException, Exception {
        if (id <= 0) {
            throw new ValidationException("Id inválido");
        }
        super.eliminar(id);
    }

    @Override
    public Cartelera buscarPorId(int id) throws ValidationException, Exception {
        if (id <= 0) {
            throw new ValidationException("Id inválido");
        }
        return super.buscarPorId(id);
    }

    @Override
    public List<Cartelera> listar() throws Exception {
        return super.listar();
    }
    
    @Override
    public List<Cartelera> listarFiltrado(Cartelera.Genero genero,
            Integer anioDesde,
            Integer anioHasta) throws Exception {
        // Validaciones suaves: solo cuando vienen valores
        if (anioDesde != null) {
            CarteleraValidator.validarAnio(anioDesde);
        }
        if (anioHasta != null) {
            CarteleraValidator.validarAnio(anioHasta);
        }
        if (anioDesde != null && anioHasta != null && anioDesde > anioHasta) {
            throw new ValidationException("Rango de años inválido: 'desde' no puede ser mayor que 'hasta'.");
        }
        
        return super.listarFiltrado(genero, anioDesde, anioHasta);
    }
}
