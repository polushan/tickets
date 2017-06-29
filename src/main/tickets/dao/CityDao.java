package dao;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import model.City;
import org.springframework.context.annotation.Bean;
import util.HibernateUtil;

import java.sql.SQLException;
import java.util.List;

public class CityDao extends BaseDao{

    public void addCity(City city) throws SQLException {
        baseAct(city, "save");
    }

    public void updateCity(City city) throws SQLException {
        baseAct(city, "update");
    }

    public void deleteCity(City city) throws SQLException {
        baseAct(city, "delete");
    }

    public City getCityById(String id) throws SQLException {
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            city = session.load(City.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;

    }

    public City getCityByName(String name) throws SQLException {
        Session session = null;
        City city = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            city = (City) session.createCriteria(City.class).add(Restrictions.eq("name", name)).uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return city;
    }
}
