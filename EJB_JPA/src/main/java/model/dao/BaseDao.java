package model.dao;


import model.entity.BaseEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 *
 *  managed ejbs will reflect changes on db https://stackoverflow.com/questions/29035348/ejb-entity-database-row-is-automatically-updated-from-entity-without-save-in-ej#
 *
 *  the returned entity form merge is managed not the entity that is passed to it
 * https://stackoverflow.com/a/1070629/3593084
 *
 * -------------------------------------------------
 * if you dont use ejb or other frameworks, you have to manage ejbs yourself like this
 * https://stackoverflow.com/questions/40635734/using-generics-and-jpa-entitymanager-methods
 */

public abstract class BaseDao <T extends BaseEntity> {

    @PersistenceContext(unitName = "MYSQLDS")
    protected EntityManager em;

    public abstract Class<T> getEntityClass();

    private void init(){
        //Since version 2.0, JPA provides easy access to the APIs of the underlying implementations. The EntityManager
        // and the EntityManagerFactory provide an unwrap method which returns the corresponding classes of the JPA implementation.
        Session session = em.unwrap(Session.class);
    }

    public T create(T entity) {
        try {
            em.persist(entity);
            return entity;
        } catch (Exception e) {
            return null;
        }
    }

    //Persist Vs Merge: https://stackoverflow.com/questions/1069992/jpa-entitymanager-why-use-persist-over-merge
    protected T updateEntity(T entity){
        return em.merge(entity);
    }

    public T find(T entity, Object primaryKey) {
        try {
            //note: getting generic type at runtime > https://stackoverflow.com/questions/3403909/get-generic-type-of-class-at-runtime
            //if you modify this object before returning it, it will be reflected in db
            return (T) em.find(getEntityClass(), primaryKey);
        } catch (Exception e) {
            return null;
        }
    }

    //doing a batch update: https://stackoverflow.com/a/31108892/3593084
    public void batchTransaction(List<T> entityList) {
        for (T itemT : entityList) {
            em.merge(itemT);
        }
        //By default, Hibernate framework will cache all the persisted objects in the session-level cache
        // and ultimately the application would fall over with an OutOfMemoryException. this is for clearing the memory
        // that hibernate cache takes.
        em.flush();
        em.clear();
        //BUT we also need to make the process faster so: hibernate.jdbc.batch_size: This tag controls the maximum
        // number of statements that Hibernate will batch together before asking the driver to execute the batch.
        // Zero or a negative number disables this feature
    }

    public List<T> findAll(String query, boolean isNamedQuery) {
        try {
            return isNamedQuery ? em.createNamedQuery(query).getResultList() : em.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<T> findById(Long userId) {
        return Optional.ofNullable(em.find(getEntityClass(), userId));
    }

}
