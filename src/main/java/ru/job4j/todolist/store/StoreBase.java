package ru.job4j.todolist.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.job4j.todolist.store.hibernate.HibernateFactory;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;


public class StoreBase<T> implements Store<T> {
    private static final Logger LOG = LogManager.getLogger(StoreBase.class);
    private SessionFactory sessionFactory = HibernateFactory.getInstance().getSessionFactory();
    private Class<T> tClass;

    private StoreBase(Class<T> tClass) {
        this.tClass = tClass;
    }

    public static <T> Store<T> getInstance(Class<T> tClass) {
        return new StoreBase<>(tClass);
    }

    private void tx(final Consumer<Session> command) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            command.accept(session);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
    }

    private List<T> find(final Function<Session, List<T>> command) {
        List<T> list = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            list = command.apply(session);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public void create(T entity) {
        this.tx(session -> session.save(entity));
    }

    @Override
    public List<T> getAll() {
            return find(session -> session.createQuery("from " + this.tClass.getName(), this.tClass).list());
    }

    @Override
    public void delete(T entity) {
        this.tx(
                session ->
                    session.delete(entity)
        );

    }

    @Override
    public void update(T entity) {
        this.tx(session ->
            session.update(entity)
        );
    }

    @Override
    public T findById(Integer id) {
        T result = null;
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            result = session.get(tClass, id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<T> findByName(String name) {
        return this.find((session ->
                            session.createQuery("from " + this.tClass.getName() + " where name = :name", this.tClass)
                            .setParameter("name", name)
                            .list()));
    }

}
