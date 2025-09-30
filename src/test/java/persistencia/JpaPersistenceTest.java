package persistencia;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javax.persistence.*;
import logica.Cartelera;

public class JpaPersistenceTest {

    @Test
    void debeCargarPUyPersistirEntidad() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("CineMagentaPU");
        assertNotNull(emf, "No se pudo crear el EMF (revisa persistence.xml y dependencias)");

        EntityManager em = emf.createEntityManager();
        assertNotNull(em, "No se pudo crear el EntityManager");

        em.getTransaction().begin();
        try {
            Cartelera c = new Cartelera();
            c.setTitulo("Test JPA");
            c.setDirector("Erik");
            c.setAnio(2000);
            c.setDuracion(100);
            c.setGenero(Cartelera.Genero.DRAMA);

            em.persist(c);
            // No hacemos commit para no dejar datos:
            em.getTransaction().rollback();
        } catch (RuntimeException ex) {
            // Si algo falla, aseguramos rollback y re-lanzamos para que el test marque error
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw ex;
        } finally {
            em.close();
            emf.close();
        }
    }
}
