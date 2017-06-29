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
import util.MailSender;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

@Controller
public class RegistrationController {

    private UserDao userDao;
    private Cryptographer crypt;
    private MailSender mailSender;

    @Autowired
    RegistrationController(UserDao userDao, Cryptographer crypt, MailSender mailSender) {
        this.userDao = userDao;
        this.crypt = crypt;
        this.mailSender = mailSender;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm() {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String validateForm(@RequestParam Map<String, String> params, ModelMap modelMap, HttpSession httpSession) {
        String email = params.get("email").trim();
        String login = params.get("login").trim();
        String password = params.get("password");
        if ("".equals(email) || "".equals(login) || "".equals(password)) {
            modelMap.addAttribute("tryAgain", "Заполните всю форму");
            return "registration";
        }
        try {
            if (userDao.getUserByEmail(email.trim()) != null) {
                modelMap.addAttribute("tryAgain", "Email уже зарегестрирован");
                return "registration";
            } else if (userDao.getUserByLogin(login) != null) {
                modelMap.addAttribute("tryAgain", "Уже есть пользователь с таким логином");
                return "registration";
            }
        } catch (SQLException e) {
            modelMap.addAttribute("errorCause", "can't to check user");
            return "error";
        }
        httpSession.setAttribute("futureUser", new User(email, login, crypt.md5Custom(password)));
        httpSession.setAttribute("code", sendPrivateCode(email));
        return "checkEmail";
    }

    @RequestMapping("/checkEmail")
    public String checkEmail(@RequestParam Map<String, String> params, ModelMap modelMap, HttpSession httpSession) {
        if (httpSession.getAttribute("code").equals(params.get("code"))) {
            User user = (User) httpSession.getAttribute("futureUser");
            httpSession.removeAttribute("futureUser");
            try {
                userDao.addUser(user);
            } catch (SQLException e) {
                modelMap.addAttribute("errorCause", "problem with registration user");
                return "error";
            }
            httpSession.setAttribute("user", user);
            return "success";
        } else {
            modelMap.addAttribute("tryAgain", "Неверный код");
            return "checkEmail";
        }
    }


    private String sendPrivateCode(String email) {
        Random rand = new Random();
        String code = "" + rand.nextLong();
        mailSender.send("your private code", code, email.trim());
        System.out.println(code);
        return code;
    }

}
