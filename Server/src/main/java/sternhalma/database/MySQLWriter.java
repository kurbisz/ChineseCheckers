package sternhalma.database;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import sternhalma.Notifer;

import java.util.HashSet;
import java.util.Set;

public class MySQLWriter implements Writer {
    private static MySQLWriter instance = null;
    /**
     * Get the instance
     * @return instance of MySQLWriter
     */
    public static MySQLWriter getInstance() {
        if (instance == null) {
            instance = new MySQLWriter();
        }
        return instance;
    }
    SessionFactory sessionFactory;
    @Override
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

    @Override
    public void addMove(MoveEntry entry) {

    }
    public MySQLWriter() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }
    public void test() {
        GameEntry game = new GameEntry(1,2,"classic");
        MoveEntry m1 = new MoveEntry(1,1,1,1,1, game,1);
        MoveEntry m2 = new MoveEntry(2,2,2,2,2, game,2);
        MoveEntry m3 = new MoveEntry(3,3,3,3,3, game,3);
        Set<MoveEntry> st = new HashSet<>();
        st.add(m1);
        st.add(m2);
        st.add(m3);
        game.setMoves(st);
        addGame(game);
    }
}
