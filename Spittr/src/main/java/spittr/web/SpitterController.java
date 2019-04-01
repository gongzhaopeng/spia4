package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.servlet.http.Part;
import javax.validation.Valid;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * Created by gongzhaopeng on 15/03/2018.
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    private SpitterRepository spitterRepository;

    @Autowired
    public SpitterController(SpitterRepository spitterRepository) {
        this.spitterRepository = spitterRepository;
    }

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute(new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            @RequestPart(value = "profilePicture", required = false) Part profilePicture,
            @Valid Spitter spitter,
            Errors errors,
            RedirectAttributes model) throws IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }

        spitterRepository.save(spitter);
        if (profilePicture != null) {
            profilePicture.write("/tmp/spittr/" + spitter.getUsername() + '.'
                    + profilePicture.getContentType().split("/")[1]);
        }

        model.addAttribute("username", spitter.getUsername());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/{username}";
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(
            @PathVariable("username") String username, Model model) {
        if (!model.containsAttribute("spitter")) {
            Spitter spitter = spitterRepository.findByUsername(username);
            model.addAttribute(spitter);
        }
        return "profile";
    }
}
