package spittr.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by gongzhaopeng on 14/03/2018.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping(method = GET)
    public String home() {
        return "home";
    }
}
