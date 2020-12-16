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
import ru.spbstu.antufievsemen.coursedatabase.entity.QueryResource;
import ru.spbstu.antufievsemen.coursedatabase.service.FactoryWorkerService;
import ru.spbstu.antufievsemen.coursedatabase.service.QueryResourceService;
import ru.spbstu.antufievsemen.coursedatabase.service.ResourceService;
import ru.spbstu.antufievsemen.coursedatabase.service.StoreWorkerService;

@Controller
@RequestMapping("/query-resource")
public class QueryResourceController {

  private final QueryResourceService queryResourceService;
  private final StoreWorkerService storeWorkerService;
  private final FactoryWorkerService factoryWorkerService;
  private final ResourceService resourceService;
  private static final Logger logger = LoggerFactory.getLogger(QueryResourceController.class);


  public QueryResourceController(QueryResourceService queryResourceService, StoreWorkerService storeWorkerService, FactoryWorkerService factoryWorkerService, ResourceService resourceService) {
    this.queryResourceService = queryResourceService;
    this.storeWorkerService = storeWorkerService;
    this.factoryWorkerService = factoryWorkerService;
    this.resourceService = resourceService;
  }

  @GetMapping("/list-queryResources")
  public String listQueryResources(Model model) {
    List<QueryResource> list = queryResourceService.listQueryResources();
    model.addAttribute("queryResources", list);
    return "views/queryResources/list-queryResources";
  }

  @GetMapping("/queryResource/{id}")
  public String showQueryResource(@PathVariable("id") long id, Model model) {
    QueryResource queryResource = queryResourceService.findQueryResourceById(id);
    model.addAttribute("queryResource", queryResource);
    return "views/queryResources/show-queryResource";
  }

  @GetMapping("/addQueryResource")
  public String newQueryResource(Model model) {
    model.addAttribute("queryResource", new QueryResource());
    return "views/queryResources/add-queryResource";
  }

  @PostMapping("/addQueryResource")
  public String addQueryResource(QueryResource queryResource) {
    if (!queryResource.getFactoryWorker().getPassportNumber().equals("")) {
      if (!(queryResource.getResource().getId() < 1)) {
        queryResource.setFactoryWorker(factoryWorkerService.
                getByPassportNumber(queryResource.getFactoryWorker().getPassportNumber()));
        queryResource.setStoreWorker(storeWorkerService.
                getByPassportNumber(queryResource.getStoreWorker().getPassportNumber()));
        queryResource.setResource(resourceService.findResourceById(queryResource.getResource().getId()));
        queryResourceService.addQueryResource(queryResource);
        return "redirect:list-queryResources";
      }
    }
    logger.info(queryResource.getFactoryWorker().getPassportNumber());
    logger.info(String.valueOf(queryResource.getResource().getId()));
    return "views/queryResources/add-queryResource";
  }

  @GetMapping("/updateQueryResource/{id}")
  public String updateQueryResource(Model model, @PathVariable("id") long id) {
    QueryResource queryResource = queryResourceService.findQueryResourceById(id);
    model.addAttribute("queryResource", queryResource);
    return "views/queryResources/update-queryResource";
  }

  @PostMapping("/updateQueryResource")
  public String editQueryResource(QueryResource queryResource) {
    queryResource.setFactoryWorker(factoryWorkerService.
            getByPassportNumber(queryResource.getFactoryWorker().getPassportNumber()));
    queryResource.setStoreWorker(storeWorkerService.
            getByPassportNumber(queryResource.getStoreWorker().getPassportNumber()));
    queryResource.setResource(resourceService.findResourceById(queryResource.getResource().getId()));
    queryResourceService.updateQueryResource(queryResource);
    return "redirect:list-queryResources";
  }

  @GetMapping("/deleteQueryResource/{id}")
  public String deleteQueryResource(@PathVariable("id")long id) {
    queryResourceService.deleteQueryResourceById(id);
    return "redirect:/query-resource/list-queryResources";
  }
}
