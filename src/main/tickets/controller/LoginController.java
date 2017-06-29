package controller;

import dao.UserDao;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import util.Cryptographer;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    private UserDao userDao;
    private Cryptographer crypt;

    @Autowired
    LoginController(UserDao userDao, Cryptographer crypt) {
        this.userDao = userDao;
        this.crypt = crypt;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showForm() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String checkUser(@RequestParam Map<String, String> params, ModelMap modelMap, HttpSession httpSession) {
        String login = params.get("login");
        String password = params.get("password");
        if (!validateLoginForm(login, password)) {
            return "login";
        }
        try {
            User user = userDao.getUserByLogin(login);
            if (user != null && user.getPassword().trim().equals(crypt.md5Custom(password))) {
                httpSession.setAttribute("user", user);
                return "success";
            } else {
                return "login";
            }
        } catch (SQLException e) {
            modelMap.addAttribute("errorCause", "can't check user");
            return "error";
        }
    }

    private boolean validateLoginForm(String login, String password) {
        if ("".equals(login.trim()) || "".equals(password.trim())) {
            return false;
        } else {
            return true;
        }
    }

}
