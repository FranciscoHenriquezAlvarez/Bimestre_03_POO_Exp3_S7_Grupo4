package logica.validacion;

import logica.Cartelera;
import logica.validacion.exceptions.ValidationException;

public final class CarteleraValidator { // Actualizacion

    private CarteleraValidator() {}

    public static void validar(Cartelera c) throws ValidationException {
        if (c == null) throw new ValidationException("Cartelera nula");
        validarTitulo(c.getTitulo());
        validarDirector(c.getDirector());
        validarAnio(c.getAnio());
        validarDuracion(c.getDuracion());
        validarGenero(c.getGenero());
    }

    public static void validarTitulo(String titulo) throws ValidationException {
        if (titulo == null || titulo.isBlank())
            throw new ValidationException("Título obligatorio");
        if (titulo.length() > 150)
            throw new ValidationException("Título demasiado largo (máx. 150)");
    }

    public static void validarDirector(String director) throws ValidationException {
        if (director == null || director.isBlank())
            throw new ValidationException("Director obligatorio");
        if (director.length() > 50)
            throw new ValidationException("Director demasiado largo (máx. 50)");
    }

    public static void validarAnio(int anio) throws ValidationException {
        if (anio < 1895 || anio > 2100)
            throw new ValidationException("Año fuera de rango (1895–2100)");
    }

    public static void validarDuracion(int duracion) throws ValidationException {
        if (duracion <= 0)
            throw new ValidationException("Duración debe ser positiva");
    }

    public static void validarGenero(Cartelera.Genero genero) throws ValidationException {
        if (genero == null)
            throw new ValidationException("Género obligatorio");
    }
}

