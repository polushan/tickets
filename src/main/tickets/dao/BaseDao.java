package dao;


import org.hibernate.Session;
import util.HibernateUtil;

import java.sql.SQLException;

public abstract class BaseDao {

    public void baseAct(Object object, String act) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.getClass().getMethod(act, Object.class).invoke(session, object);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
