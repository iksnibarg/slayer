package pl.grabinski.slayer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class InstanceController {

    private final InstanceService instanceService;
    private static final Logger log = LoggerFactory.getLogger(InstanceController.class);

    public InstanceController(InstanceService instanceService) {
        this.instanceService = instanceService;
    }

    @RequestMapping(path = "/list")
    public String list(Model model) {
        model.addAttribute("instances", instanceService.findAll());
        return "instance/list";
    }

    @RequestMapping(path = "/instance/{id}", method = RequestMethod.POST)
    public String updateNote(@PathVariable String id, String notes, Model model) {
        log.info("updating notes for instance {}: {}", id, notes);
        instanceService.updateNotes(id, notes);
        return "redirect:/list";
    }

}
