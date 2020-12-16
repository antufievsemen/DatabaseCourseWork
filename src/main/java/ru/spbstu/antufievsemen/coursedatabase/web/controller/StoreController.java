package ru.spbstu.antufievsemen.coursedatabase.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spbstu.antufievsemen.coursedatabase.entity.GradeBeer;
import ru.spbstu.antufievsemen.coursedatabase.entity.Resource;
import ru.spbstu.antufievsemen.coursedatabase.service.ResourceService;

@Controller
@RequestMapping("/store")
public class StoreController {
  private final ResourceService resourceService;
  private static final Logger logger = LoggerFactory.getLogger(QueryResourceController.class);

  public StoreController(ResourceService resourceService) {
    this.resourceService = resourceService;
  }

  @GetMapping("/list-resources")
  public String listResources(Model model) {
    List<Resource> list = resourceService.listResources();
    model.addAttribute("resources", list);
    return "views/store/list-resources";
  }

  @GetMapping("/resource/{id}")
  public String showResource(@PathVariable("id") long id, Model model) {
    Resource resource = resourceService.findResourceById(id);
    model.addAttribute("resource", resource);
    return "views/store/show-resource";
  }

  @GetMapping("/addResource")
  public String newResource(Model model) {
    model.addAttribute("resource", new Resource());
    return "views/store/add-resource";
  }

  @PostMapping("/addResource")
  public String addResource(Resource resource) {
    resourceService.addResources(resource);
    return "redirect:list-resources";
  }

  @GetMapping("/updateResource/{id}")
  public String updateResource(Model model, @PathVariable("id") long id) {
    Resource resource = resourceService.findResourceById(id);
    model.addAttribute("resource", resource);
    return "views/store/update-resource";
  }

  @PostMapping("/updateResource")
  public String editResource(Resource resource) {
    resourceService.updateResource(resource);
    return "redirect:list-resources";
  }

  @GetMapping("/deleteResource/{id}")
  public String deleteResource(@PathVariable("id")long id) {
    resourceService.deleteResourceById(id);
    return "redirect:/store/list-resources";
  }
}
