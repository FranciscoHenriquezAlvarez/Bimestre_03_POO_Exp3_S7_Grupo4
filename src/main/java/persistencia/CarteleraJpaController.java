package persistencia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import logica.Cartelera;
import persistencia.exceptions.NonexistentEntityException;

public class CarteleraJpaController implements Serializable {

    private EntityManagerFactory emf = null;

    public CarteleraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public CarteleraJpaController() {
        this.emf = Persistence.createEntityManagerFactory("CineMagentaPU");
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

     public void create(Cartelera cartelera) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cartelera);
            em.getTransaction().commit();
        } finally { if (em.isOpen()) em.close(); }
    }

    public void edit(Cartelera cartelera) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(cartelera);
            em.getTransaction().commit();
        } finally { if (em.isOpen()) em.close(); }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            Cartelera ref;
            try {
                ref = em.getReference(Cartelera.class, id);
                ref.getId(); // fuerza EntityNotFoundException si no existe
            } catch (EntityNotFoundException ex) {
                em.getTransaction().rollback();
                throw new NonexistentEntityException("No existe la película con id=" + id, ex);
            }
            em.remove(ref);
            em.getTransaction().commit();
        } finally { if (em.isOpen()) em.close(); }
    }

    public Cartelera findPelicula(int id) {
        EntityManager em = getEntityManager();
        try { return em.find(Cartelera.class, id); }
        finally { if (em.isOpen()) em.close(); }
    }

    @SuppressWarnings("unchecked")
    public List<Cartelera> findCarteleraEntities() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery<Cartelera> cq = em.getCriteriaBuilder().createQuery(Cartelera.class);
            cq.select(cq.from(Cartelera.class));
            return em.createQuery(cq).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally { if (em.isOpen()) em.close(); }
    }
    
    public List<Cartelera> buscarPorFiltros(Cartelera.Genero genero, Integer anioDesde, Integer anioHasta) { // Actualizacion
        EntityManager em = getEntityManager();
        try {
            var cb = em.getCriteriaBuilder();
            var cq = cb.createQuery(Cartelera.class);
            var root = cq.from(Cartelera.class);

            List<javax.persistence.criteria.Predicate> ps = new java.util.ArrayList<>();
            if (genero != null) {
                ps.add(cb.equal(root.get("genero"), genero));
            }
            if (anioDesde != null) {
                ps.add(cb.greaterThanOrEqualTo(root.get("anio"), anioDesde));
            }
            if (anioHasta != null) {
                ps.add(cb.lessThanOrEqualTo(root.get("anio"), anioHasta));
            }

            cq.select(root)
              .where(ps.toArray(new javax.persistence.criteria.Predicate[0]))
              .orderBy(cb.asc(root.get("id")));
            return em.createQuery(cq).getResultList();
        } finally {
            if (em.isOpen()) em.close();
        }
    }
}