/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import domain.Kweet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Luc
 */
public class KweetDAO extends EntityDAO<Kweet> {

    @PersistenceContext(name = "KwetterPU")
    private EntityManager em;

    public KweetDAO(Class<Kweet> entityClass) {
        super(entityClass);
    }

    public Kweet create(Kweet entity) {
        if (!entity.getText().isEmpty()) {
            if (entity.getText().length() < 140 && entity.getText().length() > 0) {
                em.persist(entity);
                return entity;
            }
        }

        return null;
    }

    public List<Kweet> getByText(String arg) {
        TypedQuery<Kweet> q = em.createNamedQuery("Kweet.findByText", Kweet.class).setParameter("text", arg);
        List<Kweet> kweets = q.getResultList();
        return kweets;
    }

    public void delete(Kweet k) {
        EntityTransaction transaction = em.getTransaction();
        em.remove(k);
        transaction.commit();

    }

    public List<Kweet> getKweetsByUId(long id) {
        TypedQuery<Kweet> q = em.createNamedQuery("Kweet.findByUId", Kweet.class).setParameter("id", id);
        List<Kweet> kweets = q.getResultList();
        return kweets;
    }

}
