package pl.grabinski.slayer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class InstanceController {

    private final InstanceService instanceService;

    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @RequestMapping(path = "/list")
    public String list(Model model) {
        model.addAttribute("instances", instanceService.findAll());
        return "instance/list";
    }

}
