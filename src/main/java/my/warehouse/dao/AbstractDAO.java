package my.warehouse.dao;

import my.warehouse.exceptions.DataNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.List;

public class AbstractDAO<T extends Serializable> {
    protected final SessionFactory sessionFactory;

    private final Class<T> clazz;

    public AbstractDAO(SessionFactory sessionFactory, Class<T> clazz) {
        this.sessionFactory = sessionFactory;
        this.clazz = clazz;
    }

    public List<T> select() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from " + clazz.getName(), clazz).getResultList();
    }

    public T select(long id) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.load(clazz, id);
        } catch (Exception exception){
            throw new DataNotFoundException("There is no record of a " + clazz.getName() + " with id = " + id + " in the database");
        }
    }

    public long insert(T value) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        long id = (long) session.save(value);
        session.getTransaction().commit();
        return id;
    }

    public void update(long id, T newValue) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(newValue);
        session.getTransaction().commit();
    }

    public void delete(long id) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.remove(select(id));
        session.getTransaction().commit();
    }
}
