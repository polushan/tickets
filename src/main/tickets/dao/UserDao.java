package dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import model.Request;
import model.User;
import util.HibernateUtil;

public class UserDao extends BaseDao{


    public void addUser(User user) throws SQLException {
        baseAct(user, "save");
    }

    public void updateUser(User user) throws SQLException {
        baseAct(user, "update");
    }

    public void deleteUser(User user) throws SQLException {
        baseAct(user, "delete");
    }

    public User getUserById(long id) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = session.load(User.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public void deleteHistory(User user) throws SQLException {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            for (Request req : user.getHistory()) {
                session.delete(req);
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    public List<Request> getHistory(User user) throws SQLException {
        return user.getHistory();
    }

    public User getUserByEmail(String email) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    public User getUserByLogin(String login) throws SQLException {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createCriteria(User.class).add(Restrictions.eq("login", login)).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
}
