package it.betacom.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.betacom.model.Animale;

public class AnimaleDAO {
    private EntityManagerFactory emf;

    public AnimaleDAO() {
        emf = Persistence.createEntityManagerFactory("PetShopWeb"); // Replace "PU_NAME" with your persistence unit name
    }

    public List<Animale> getTuttiAnimali() {
        EntityManager em = emf.createEntityManager();
        List<Animale> listaAnimali = em.createQuery("SELECT a FROM Animale a", Animale.class).getResultList();
        em.close();
        return listaAnimali;
    }

    public void aggiungiAnimale(Animale animale) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(animale);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void aggiornaAnimale(Animale animale) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(animale);
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void eliminaAnimale(int matricola) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Animale animale = em.find(Animale.class, matricola);
            if (animale != null) {
                em.remove(animale);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Animale trovaAnimalePerMatricola(int matricola) {
        EntityManager em = emf.createEntityManager();
        Animale animale = em.find(Animale.class, matricola);
        em.close();
        return animale;
    }
    public List<Animale> trovaAnimaliCliente(int idCliente) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Animale> query = em.createQuery("SELECT a FROM Animale a WHERE a.cliente.idCliente = :idCliente", Animale.class);
        query.setParameter("idCliente", idCliente);
        List<Animale> animaliCliente = query.getResultList();
        em.close();
        return animaliCliente;
    }

    // Altri metodi DAO se necessario
}
