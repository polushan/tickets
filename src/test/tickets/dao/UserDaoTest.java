package tickets.dao;

import dao.UserDao;
import org.junit.runner.RunWith;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import static org.junit.Assert.*;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-cfg.xml")
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void addUser() throws Exception {
        User testUser = new User("test2", "test2", "098f6bcd4621d373cade4e832627b4f6");
        userDao.addUser(testUser);
    }

    @Test
    public void updateUser() throws Exception {
        User testUser = new User("test2", "test", "test");
        userDao.updateUser(testUser);
    }

    @Test
    public void deleteUser() throws Exception {
        User testUser = new User("test2", "test", "test");
        userDao.deleteUser(testUser);
    }

    @Test
    public void getHistory() throws Exception {

    }

    @Test
    public void getUserByEmail() throws Exception {
        User testUser = new User("test", "test", "test");
        assertEquals(testUser.getEmail(), userDao.getUserByEmail("test").getEmail());
    }

    @Test
    public void getUserByLogin() throws Exception {
        User testUser = new User("test", "test", "test");
        assertEquals(testUser.getLogin(), userDao.getUserByLogin("test").getLogin());
    }

}