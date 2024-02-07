package it.betacom.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import it.betacom.connection.DatabaseConnection;
import it.betacom.model.User;

public class UserDAO {
	private final EntityManagerFactory emf;

    public UserDAO() {
        emf = Persistence.createEntityManagerFactory("PetShopWeb");
    }

    public void aggiungiCliente(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> getTuttiClienti() throws SQLException {
    	
    	        List<User> userList = new ArrayList<>();
    	        String sql = "SELECT * FROM User";
    	        Connection connection= DatabaseConnection.getConnection();
    	        try (PreparedStatement stmt = connection.prepareStatement(sql);
    	             ResultSet rs = stmt.executeQuery()) {
    	            
    	            while (rs.next()) {
    	                User user = new User();
    	                user.setUsername(rs.getString("username"));
    	                user.setNome(rs.getString("nome"));
    	                user.setCognome(rs.getString("cognome"));
    	                user.setDataDiNascita(rs.getString("dataDiNascita"));
    	                user.setPassword(rs.getString("password"));
    	                user.setRuolo(rs.getString("ruolo"));
    	                user.setStato(rs.getString("stato"));
    	                user.setEmail(rs.getString("email"));
    	                user.setCellulare(rs.getString("cellulare"));
    	                
    	                userList.add(user);
    	            }
    	        }
    	        
    	        return userList;
    	    }
     
    

    public User getClientePerUsername(String username) {
        EntityManager em = emf.createEntityManager();
        User user = em.find(User.class, username);
        em.close();
        return user;
    }

    public void aggiornaCliente(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();
    }

    public void rimuoviCliente(Long id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
        em.getTransaction().commit();
        em.close();
    }

    public boolean isUsernameInUse(String username) {
        EntityManager em = emf.createEntityManager();
        try {
            // Query per contare quante occorrenze di uno username ci sono nel database
            TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class);
            query.setParameter("username", username);
            Long count = query.getSingleResult();
            return count > 0;
        } catch (Exception e) {
            return false;
        } finally {
            em.close();
        }
    }
    public String createUsername(String nome, String cognome, String dataDiNascita) {
        String usernameBase = (nome.substring(0, 2) + cognome.substring(0, 2) + dataDiNascita.substring(0, 2)).toLowerCase();
        String username = usernameBase;
        int count = 0;
        UserDAO c = new UserDAO();

        while (c.isUsernameInUse(username)) {
            count++;
            username = usernameBase +"_"+ count;
        }

        return username;
    }

    
    public boolean verificaPassword(String username, String password) {
        EntityManager em = emf.createEntityManager();
        try {
            // Query per ottenere il cliente con il nome utente specificato
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);

            User user = query.getSingleResult();

            // Verifica se il cliente esiste e se la password corrisponde
            if (user != null && user.getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        } catch (NoResultException e) {
            // Se non viene trovato un cliente con il nome utente specificato, restituisci false
            return false;
        } finally {
            em.close();
        }
    }
    public void updateUser(User updatedUser) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            User user = em.find(User.class, updatedUser.getUsername());
            if (user != null) {
                // Aggiorna i dati dell'utente con quelli dell'oggetto updatedUser
                user.setNome(updatedUser.getNome());
                user.setCognome(updatedUser.getCognome());
                user.setDataDiNascita(updatedUser.getDataDiNascita());
                user.setPassword(updatedUser.getPassword());
                user.setRuolo(updatedUser.getRuolo());
                user.setStato(updatedUser.getStato());
                user.setEmail(updatedUser.getEmail());
                user.setCellulare(updatedUser.getCellulare());

                // Effettua la commit della transazione
                transaction.commit();
            } else {
                // Utente non trovato nel database
                System.out.println("Utente non trovato.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Chiudi l'EntityManager
            em.close();
        }
    }    	
    public void updateUserStatus(String userId, String newStatus) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            // Trova l'utente nel database utilizzando l'ID o username
            User user = em.find(User.class, userId);
            if (user != null) {
                // Aggiorna lo stato dell'utente con il nuovo stato
                user.setStato(newStatus);

                // Effettua la commit della transazione
                transaction.commit();
            } else {
                // Utente non trovato nel database
                System.out.println("Utente non trovato.");
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            // Chiudi l'EntityManager
            em.close();
        }
    }
    }
