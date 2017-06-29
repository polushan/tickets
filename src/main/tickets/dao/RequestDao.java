package dao;

import org.hibernate.Session;
import model.Request;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class RequestDao extends BaseDao{

    public void addRequest(Request request) throws SQLException {
        baseAct(request, "save");
    }


    public void deleteRequest(Request request) throws SQLException {
       baseAct(request, "delete");
    }


    public Request getRequestById(long id) throws SQLException {
        Session session = null;
        Request request = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            request = session.load(Request.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        if (request == null) {
            return new Request();
        } else {
            return request;
        }
    }
}
