package logica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
        name = "cartelera",
        indexes = {
            @Index(name = "idx_cartelera_genero_anio", columnList = "genero, anio"),
            @Index(name = "idx_cartelera_anio", columnList = "anio")
        }) // Actualizacion

public class Cartelera implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false, length = 150) // Actualizacion
    private String titulo;
    
    @Column(nullable = false, length = 50) // Actualizacion
    private String director;
    
    @Column(nullable = false) // Actualizacion
    private int anio; // Cambiado de String a int
    
    @Column(nullable = false) // Actualizacion
    private int duracion; // Cambiado de String a int
    
    @Enumerated(EnumType.STRING) // Actualizacion
    @Column(nullable = false, length = 50)
    private Genero genero; // Cambiado de String a Enum

    public enum Genero {
        COMEDIA, DRAMA, ACCION, TERROR, CIENCIA_FICCION, AVENTURA
    }

    // Constructor completo
    public Cartelera(int id, String titulo, String director, int anio, int duracion, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.anio = anio;
        this.duracion = duracion;
        this.genero = genero;
    }

    // Constructor por defecto
    public Cartelera() {
        // Constructor vacío necesario para JPA
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    @Override // Actualizacion
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cartelera)) {
            return false;
        }
        Cartelera other = (Cartelera) o;
        return this.id == other.id;
    }

    @Override // Actualizacion
    public int hashCode() {
        return Integer.hashCode(id);
    }
}