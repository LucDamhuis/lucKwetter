/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;

/**
 *
 * @author Luc
 */
public abstract class EntityDAO<T> {

    private Class<T> entityClass;

    EntityDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    @PersistenceContext(unitName = "KwetterPU")
    private EntityManager entityManager;

    public void setEntityManager(EntityManager em) {
        entityManager = em;
    }

    public T getWithId(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        T entity = entityManager.find(entityClass, id);
        transaction.commit();
        return entity;
    }

    public List<T> getAll() {
        EntityTransaction transaction = entityManager.getTransaction();
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> c = cq.from(entityClass);
        cq.select(c);
        List<T> all = entityManager.createQuery(cq).getResultList();
        transaction.commit();
        return all;
    }

    public void delete(Long id) throws NullPointerException {

        EntityTransaction transaction = entityManager.getTransaction();

        entityManager.remove(entityManager.find(entityClass, id));

        transaction.commit();
    }
}
