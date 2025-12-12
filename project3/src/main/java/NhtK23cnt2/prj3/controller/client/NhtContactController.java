package NhtK23cnt2.prj3.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NhtContactController {

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "NhtContact";   // resources/templates/NhtContact.html
    }
}
