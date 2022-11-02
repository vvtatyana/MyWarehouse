package my.warehouse.dao;

import my.warehouse.dto.WarehouseDTO;
import my.warehouse.models.Warehouse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WarehouseDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public WarehouseDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Warehouse> select(){
        Session session = sessionFactory.openSession();
        List<Warehouse> warehouses = session.createQuery("select w from Warehouse w", Warehouse.class).getResultList();
        session.close();
        return warehouses;
    }

    public Warehouse select(long id){
        Session session = sessionFactory.openSession();
        Warehouse warehouse = session.createQuery("select w from Warehouse w where id =: value", Warehouse.class)
                .setParameter("value", id).getResultList().stream().findFirst().orElse(null);
        session.close();
        return warehouse;
    }
    public long getId(WarehouseDTO warehouse){
        Session session = sessionFactory.openSession();
        Long id = session.createQuery("select w.id from Warehouse w where name =: value", Long.class)
                .setParameter("value", warehouse.getName())
                .getResultList().stream().findFirst().orElse(null);
        session.close();
        if (id != null)
            return id;
        else return 0;
    }

    public long insert(Warehouse warehouse) {
        Session session = sessionFactory.openSession();
        long id = (Long) session.save(warehouse);
        session.close();
        return id;
    }

    public void update(long id, Warehouse newWarehouse) {
        Session session = sessionFactory.openSession();
        Warehouse warehouse = select(id);
        warehouse.setName(newWarehouse.getName());
        session.beginTransaction();
        session.update(warehouse);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteDictionary(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.remove(session.get(Warehouse.class, id));
        session.getTransaction().commit();
        session.close();
    }

}
