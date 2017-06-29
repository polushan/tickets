package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @RequestMapping(method = RequestMethod.GET)
    public String showHistory() {
        return "history";
    }
}
