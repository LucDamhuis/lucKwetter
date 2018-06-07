/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luc
 */
public class UserDAO extends EntityDAO<User> {

    @PersistenceContext(name = "KwetterPU")
    private EntityManager em;

    public UserDAO(Class<User> entityClass) {
        super(entityClass);
    }

    @Override
    public User getWithId(long id) {
        EntityTransaction transaction = em.getTransaction();
        User entity = null;
        entity = em.find(User.class, id);
        transaction.commit();
        return entity;
    }

    public User update(User entity) {
        return em.merge(entity);
    }

    public User getByUsername(String username) {
        TypedQuery<User> q = em.createNamedQuery("User.getByUsername", User.class).setParameter("username", username);
        User u = q.getSingleResult();
        return u;
    }

    public User getByEmail(String email) {
        TypedQuery<User> q = em.createNamedQuery("User.getByEmail", User.class).setParameter("email", email);
        User u = q.getSingleResult();
        return u;
    }

    public User create(User u) {
        em.persist(u);
        return u;
    }

}
