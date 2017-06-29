package controller;

import dao.CityDao;
import dao.RequestDao;
import model.City;
import model.Request;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.w3c.dom.Document;
import util.Answer;
import util.RequestSender;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@RequestMapping("/")
public class IndexController {

    private CityDao cityDao;
    private RequestDao requestDao;
    private RequestSender requestSender;

    @Autowired
    public IndexController(CityDao cityDao, RequestDao requestDao, RequestSender requestSender) {
        this.cityDao = cityDao;
        this.requestDao = requestDao;
        this.requestSender = requestSender;
    }

    @RequestMapping("/")
    public String showHome(){
        return "index";
    }

    @RequestMapping("/search")
    public String search(@RequestParam Map<String, String> params, ModelMap modelMap,  HttpSession httpSession) {
        Request userRequest;
        try {
            userRequest = getValidRequest(params);
        } catch (SQLException e) {
            modelMap.addAttribute("errorCause", "can't to get valid request");
            return "error";
        }
        if (userRequest == null) {
            return "index";
        }
        try {
            Document doc = requestSender.sendRequest(userRequest, params.get("date"));
            if (doc != null) {
                modelMap.addAttribute("timetable", new Answer(doc));
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            modelMap.addAttribute("errorCause", "Ничего не найдено");
            return "error";
        }
        try {
            saveRequest(userRequest, httpSession);
        } catch (SQLException e) {
            modelMap.addAttribute("errorCause", "can't to save user request");
            return "error";
        }
        return "timetable";
    }

    private void saveRequest(Request request, HttpSession httpSession) throws SQLException {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            request.setUserId(user);
            requestDao.addRequest(request);
        } else {
            httpSession.setAttribute("lastRequest", request);
        }
    }

    private Request getValidRequest(Map<String, String> params) throws SQLException {
        Request userRequest = new Request();
        String from = params.get("from").trim();
        String to = params.get("to").trim();
        if (!"".equals(from) && !"".equals(to)) {
            City fromCity = cityDao.getCityByName(from.trim());
            City toCity = cityDao.getCityByName(to.trim());
            DateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd");
            if (fromCity != null && toCity != null) {
                String date = params.get("date");
                try {
                    if (!"".equals(date)) {
                        userRequest.setDate(dateForm.parse(date));
                    }
                } catch (ParseException e) {
                    params.put("date", null);
                }
                userRequest.setFrom(fromCity);
                userRequest.setTo(toCity);
                userRequest.setTransportType(params.get("transport_type"));
            } else {
                userRequest = null;
            }
        } else {
            userRequest = null;
        }
        return userRequest;
    }

}