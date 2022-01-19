package sternhalma.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.HashSet;
import java.util.Set;
@Deprecated
public class MySQLWriterDeprecated {
    private static MySQLWriterDeprecated instance = null;
    private SessionFactory sessionFactory;
    /**
     * Get the instance
     * @return instance of MySQLWriter
     */
    public static MySQLWriterDeprecated getInstance() {
        if (instance == null) {
            instance = new MySQLWriterDeprecated();
        }
        return instance;
    }

    /**
     * Save game representation together with moves
     * @param entry game representation
     */
    public void addGame(GameEntry entry) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        try {
            session.save(entry);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public MySQLWriterDeprecated() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

}
