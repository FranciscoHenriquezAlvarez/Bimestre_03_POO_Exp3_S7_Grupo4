package logica;

import persistencia.ControladoraPersistencia;


 // ⚠️ Código en desuso (no se ocupa en la versión con MVC y servicios JPA).
 // Se mantiene solo como referencia de la primera etapa del proyecto.
 
@Deprecated
public class Controladora {
    
    ControladoraPersistencia controladoraPersistencia = new ControladoraPersistencia();

    public void guardar(String nombrePelicula, String nombreDirector, int anioPelicula, 
                      int duracionPelicula, Cartelera.Genero generoPelicula) {
        
        Cartelera cartelera = new Cartelera();
        cartelera.setTitulo(nombrePelicula);
        cartelera.setDirector(nombreDirector);
        cartelera.setAnio(anioPelicula);
        cartelera.setDuracion(duracionPelicula);
        cartelera.setGenero(generoPelicula);
        
        controladoraPersistencia.guardar(cartelera);
    }
}