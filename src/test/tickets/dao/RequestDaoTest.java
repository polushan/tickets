package tickets.dao;

import dao.CityDao;
import dao.RequestDao;
import dao.UserDao;
import model.Request;
import model.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class RequestDaoTest {

    private UserDao userDao = new UserDao();
    private RequestDao requestDao = new RequestDao();
    private CityDao cityDao = new CityDao();


    @Test
    public void addRequest() throws Exception {
        User user = userDao.getUserByEmail("test");
        Request request = new Request();
        request.setFrom(cityDao.getCityByName("Саратов"));
        request.setTo(cityDao.getCityByName("Москва"));
        request.setTransportType("train");
        request.setUserId(user);
        requestDao.addRequest(request);
    }

    @Test
    public void deleteRequest() throws Exception {

    }

    @Test
    public void getRequestById() throws Exception {

    }

}