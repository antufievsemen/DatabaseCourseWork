package ru.spbstu.antufievsemen.coursedatabase.web.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spbstu.antufievsemen.coursedatabase.entity.FactoryWorker;
import ru.spbstu.antufievsemen.coursedatabase.entity.GradeBeer;
import ru.spbstu.antufievsemen.coursedatabase.entity.QueryPart;
import ru.spbstu.antufievsemen.coursedatabase.service.FactoryWorkerService;
import ru.spbstu.antufievsemen.coursedatabase.service.GradeBeerService;
import ru.spbstu.antufievsemen.coursedatabase.service.ManagerService;
import ru.spbstu.antufievsemen.coursedatabase.service.QueryPartService;


@Controller
@RequestMapping("/query-part")
public class QueryPartController {

  private final QueryPartService queryPartService;
  private final FactoryWorkerService factoryWorkerService;
  private final ManagerService managerService;
  private final GradeBeerService gradeBeerService;
  private static final Logger logger = LoggerFactory.getLogger(QueryPartController.class);


  public QueryPartController(QueryPartService queryPartService, FactoryWorkerService factoryWorkerService, ManagerService managerService, GradeBeerService gradeBeerService) {
    this.queryPartService = queryPartService;
    this.factoryWorkerService = factoryWorkerService;
    this.managerService = managerService;
    this.gradeBeerService = gradeBeerService;
  }

  @GetMapping("/list-parts")
  public String listQueryParts(Model model) {
    List<QueryPart> list = queryPartService.listQueryParts();
    model.addAttribute("parts", list);
    return "views/queryParts/list-parts";
  }

  @GetMapping("/part/{id}")
  public String showQueryPart(@PathVariable("id") long id, Model model) {
    QueryPart queryPart = queryPartService.findQueryPartById(id);
    model.addAttribute("part", queryPart);
    return "views/queryParts/show-part";
  }

  @GetMapping("/addQueryPart")
  public String newQueryPart(Model model) {
    model.addAttribute("part", new QueryPart());
    return "views/queryParts/add-part";
  }

  @PostMapping("/addQueryPart")
  public String addQueryPart(QueryPart queryPart) {
    if (!queryPart.getManager().getPassportNumber().equals("")) {
      if (!(queryPart.getGradeBeer().getId() < 1)) {
        queryPart.setFactoryWorker(factoryWorkerService.
                getByPassportNumber(queryPart.getFactoryWorker().getPassportNumber()));
        queryPart.setManager(managerService.getByPassportNumber(queryPart.getManager().getPassportNumber()));
        queryPart.setGradeBeer(gradeBeerService.findGradeBeerById(queryPart.getGradeBeer().getId()));
        queryPartService.addQueryPart(queryPart);
        return "redirect:list-parts";
      }
    }
    return "views/queryParts/add-part";
  }

  @GetMapping("/updateQueryPart/{id}")
  public String updateQueryPart(Model model, @PathVariable("id") long id) {
    QueryPart queryPart = queryPartService.findQueryPartById(id);
    model.addAttribute("part", queryPart);
    return "views/queryParts/update-part";
  }

  @PostMapping("/updateQueryPart")
  public String editQueryPart(QueryPart queryPart) {
    queryPart.setFactoryWorker(factoryWorkerService.
            getByPassportNumber(queryPart.getFactoryWorker().getPassportNumber()));
    queryPart.setManager(managerService.getByPassportNumber(queryPart.getManager().getPassportNumber()));
    queryPart.setGradeBeer(gradeBeerService.findGradeBeerById(queryPart.getGradeBeer().getId()));
    queryPartService.updateQueryPart(queryPart);
    return "redirect:list-parts";
  }

  @GetMapping("/deleteQueryPart/{id}")
  public String deleteQueryPart(@PathVariable("id")long id) {
    queryPartService.deleteQueryPartById(id);
    return "redirect:/query-part/list-parts";
  }
}
