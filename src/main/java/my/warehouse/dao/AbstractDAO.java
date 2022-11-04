package my.warehouse.dao;

import my.warehouse.exceptions.DataNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    public List<T> select(String name)  {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from " + clazz.getName() + " where name =: value", clazz)
                    .setParameter("value", name)
                    .getResultList();
    }

    @Transactional
    public long insert(T value) {
        Session session = sessionFactory.getCurrentSession();
        return (long) session.save(value);
    }

    @Transactional
    public void update(long id, T newValue) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.update(newValue);
    }

    @Transactional
    public void delete(long id) throws DataNotFoundException {
        Session session = sessionFactory.getCurrentSession();
        session.remove(select(id));
    }
}
