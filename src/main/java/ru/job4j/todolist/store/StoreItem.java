package ru.job4j.todolist.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.todolist.model.Item;
import ru.job4j.todolist.store.hibernate.HibernateFactory;

import java.util.List;


public class StoreItem implements Store {
    private static final Logger LOG = LogManager.getLogger(StoreItem.class);
    private SessionFactory sessionFactory = HibernateFactory.getInstance().getSessionFactory();

    public static Store getInstance() {
        return HolderStoreItem.INSTATCE;
    }

    private static final class HolderStoreItem {
        private static final Store INSTATCE = new StoreItem();
    }

    @Override
    public void create(Item item) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Item> getAll() {
        List items = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            items = session.createQuery("from ru.job4j.todolist.model.Item").list();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return items;
    }

    @Override
    public void delete(Integer id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Item entity = session.load(Item.class, id);
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(Integer id) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            Item entity = session.load(Item.class, id);
            boolean value = entity.getDone();
            entity.setDone(!value);
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }

    }
}
