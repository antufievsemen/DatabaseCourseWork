package ru.spbstu.antufievsemen.coursedatabase.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spbstu.antufievsemen.coursedatabase.entity.GradeBeer;
import ru.spbstu.antufievsemen.coursedatabase.service.GradeBeerService;


@Controller
@RequestMapping("/gradeBeer")
public class GradeBeerController {

  private final GradeBeerService gradeBeerService;
  private static final Logger logger = LoggerFactory.getLogger(QueryPartController.class);


  public GradeBeerController(GradeBeerService gradeBeerService) {
    this.gradeBeerService = gradeBeerService;
  }

  @GetMapping("/list-beerGrades")
  public String listBeerGrades(Model model) {
    List<GradeBeer> list = gradeBeerService.listGradeBeers();
    model.addAttribute("beerGrades", list);
    return "views/beerGrades/list-beerGrades";
  }

  @GetMapping("/beerGrade/{id}")
  public String showBeerGrade(@PathVariable("id") long id, Model model) {
    GradeBeer gradeBeer = gradeBeerService.findGradeBeerById(id);
    model.addAttribute("gradeBeer", gradeBeer);
    return "views/beerGrades/show-gradeBeer";
  }

  @GetMapping("/addGradeBeer")
  public String newGradeBeer(Model model) {
    model.addAttribute("gradeBeer", new GradeBeer());
    return "views/beerGrades/add-gradeBeer";
  }

  @PostMapping("/addGradeBeer")
  public String addGradeBeer(GradeBeer gradeBeer) {
    gradeBeerService.addGradeBeer(gradeBeer);
    return "redirect:list-beerGrades";
  }

  @GetMapping("/updateGradeBeer/{id}")
  public String updateGradeBeer(Model model, @PathVariable("id") long id) {
    GradeBeer gradeBeer = gradeBeerService.findGradeBeerById(id);
    model.addAttribute("gradeBeer", gradeBeer);
    return "views/beerGrades/update-gradeBeer";
  }

  @PostMapping("/updateGradeBeer")
  public String editGradeBeer(GradeBeer gradeBeer) {
    gradeBeerService.updateGradeBeer(gradeBeer);
    return "redirect:list-beerGrades";
  }
  
  @GetMapping("/deleteGradeBeer/{id}")
  public String deleteGradeBeer(@PathVariable("id")long id) {
    gradeBeerService.deleteGradeBeerById(id);
    return "redirect:/gradeBeer/list-beerGrades";
  }
}
