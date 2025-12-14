package k23cnt3.QxtMerryChristmas.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QxtContactController {

    @GetMapping("/contact")
    public String contactPage(Model model) {
        return "QxtContact";   // resources/templates/QxtContact.html
    }
}
