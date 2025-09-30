package logica.decorador;

import java.util.List;
import logica.Cartelera; 
import logica.ICarteleraService;

public abstract class CarteleraServiceDecorator implements ICarteleraService {  // Actualización

    protected final ICarteleraService wrappee;

    protected CarteleraServiceDecorator(ICarteleraService wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void guardar(Cartelera c) throws Exception {
        wrappee.guardar(c);
    }

    @Override
    public void actualizar(Cartelera c) throws Exception {
        wrappee.actualizar(c);
    }

    @Override
    public void eliminar(int id) throws Exception {
        wrappee.eliminar(id);
    }

    @Override
    public Cartelera buscarPorId(int id) throws Exception {
        return wrappee.buscarPorId(id);
    }

    @Override
    public List<Cartelera> listar() throws Exception {
        return wrappee.listar();
    }
}

