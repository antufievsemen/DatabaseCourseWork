package ru.spbstu.antufievsemen.coursedatabase.web.controller;

import java.util.List;
import org.apache.catalina.Store;
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
import ru.spbstu.antufievsemen.coursedatabase.entity.Manager;
import ru.spbstu.antufievsemen.coursedatabase.entity.Resource;
import ru.spbstu.antufievsemen.coursedatabase.entity.StoreWorker;
import ru.spbstu.antufievsemen.coursedatabase.entity.User;
import ru.spbstu.antufievsemen.coursedatabase.service.FactoryWorkerService;
import ru.spbstu.antufievsemen.coursedatabase.service.ManagerService;
import ru.spbstu.antufievsemen.coursedatabase.service.StoreWorkerService;

@Controller
@RequestMapping("/personal")
public class PersonalController {
  private final ManagerService managerService;
  private final FactoryWorkerService factoryWorkerService;
  private final StoreWorkerService storeWorkerService;
  private static final Logger logger = LoggerFactory.getLogger(QueryPartController.class);

  public PersonalController(ManagerService managerService, FactoryWorkerService factoryWorkerService, StoreWorkerService storeWorkerService) {
    this.managerService = managerService;
    this.factoryWorkerService = factoryWorkerService;
    this.storeWorkerService = storeWorkerService;
  }

  private boolean validityUser(User user, String passportNumber) {
    if (!user.getUsername().equals("")) {
      if (!user.getPassword().equals("")) {
        if (!managerService.existsByPassportNumber(passportNumber)) {
          if (!factoryWorkerService.existsByPassportNumber(passportNumber)) {
            if (!storeWorkerService.existsByPassportNumber(passportNumber)) {
              return true;
            }
          }
        }
      }
    }
    return false;
  }


  @GetMapping("/list-managers")
  public String listManagers(Model model) {
    List<Manager> list = managerService.listManagers();
    model.addAttribute("managers", list);
    return "views/personal/list-managers";
  }

  @GetMapping("/manager/{id}")
  public String showManager(@PathVariable("id") long id, Model model) {
    Manager manager = managerService.findManagerById(id);
    model.addAttribute("manager", manager);
    return "views/personal/show-manager";
  }

  @GetMapping("/list-factoryWorkers")
  public String listFactoryWorkers(Model model) {
    List<FactoryWorker> list = factoryWorkerService.listFactoryWorkers();
    model.addAttribute("factoryWorkers", list);
    return "views/personal/list-factoryWorkers";
  }

  @GetMapping("/factoryWorker/{id}")
  public String showFactoryWorker(@PathVariable("id") long id, Model model) {
    FactoryWorker factoryWorker = factoryWorkerService.findFactoryWorkerById(id);
    model.addAttribute("factoryWorker", factoryWorker);
    return "views/personal/show-factoryWorker";
  }

  @GetMapping("/list-storeWorkers")
  public String listStoreWorkers(Model model) {
    List<StoreWorker> list = storeWorkerService.listStoreWorker();
    model.addAttribute("storeWorkers", list);
    return "views/personal/list-storeWorkers";
  }

  @GetMapping("/storeWorker/{id}")
  public String showStoreWorkers(@PathVariable("id") long id, Model model) {
    StoreWorker storeWorker = storeWorkerService.findStoreWorkerById(id);
    model.addAttribute("storeWorker", storeWorker);
    return "views/personal/show-storeWorker";
  }

  @GetMapping("/addManager")
  public String newManager(Model model) {
    model.addAttribute("manager", new Manager());
    return "views/personal/add-manager";
  }

  @PostMapping("/addManager")
  public String addManager(Manager manager) {
    if (validityUser(manager.getUser(), manager.getPassportNumber())) {
      managerService.addManager(manager);
      return "redirect:list-managers";
    }
    return "views/personal/add-manager";
  }

  @GetMapping("/addFactoryWorker")
  public String newFactoryWorker(Model model) {
    model.addAttribute("factoryWorker", new FactoryWorker());
    return "views/personal/add-factoryWorker";
  }

  @PostMapping("/addFactoryWorker")
  public String addFactoryWorker(FactoryWorker factoryWorker) {
    if (validityUser(factoryWorker.getUser(), factoryWorker.getPassportNumber())) {
      factoryWorkerService.addFactoryWorker(factoryWorker);
      return "redirect:list-factoryWorkers";
    }
    return "views/personal/add-factoryWorker";
  }

  @GetMapping("/addStoreWorker")
  public String newStoreWorker(Model model) {
    model.addAttribute("storeWorker", new StoreWorker());
    return "views/personal/add-storeWorker";
  }

  @PostMapping("/addStoreWorker")
  public String addStoreWorker(StoreWorker storeWorker) {
    if (validityUser(storeWorker.getUser(), storeWorker.getPassportNumber())) {
      storeWorkerService.addStoreWorker(storeWorker);
      return "redirect:list-storeWorkers";
    }
    return "views/personal/add-storeWorker";
  }

  @GetMapping("/updateManager/{id}")
  public String updateManager(Model model, @PathVariable("id") long id) {
    Manager manager = managerService.findManagerById(id);
    model.addAttribute("manager", manager);
    logger.info(manager.fullInfo());
    return "views/personal/update-manager";
  }

  @PostMapping("/updateManager")
  public String editManager(Manager manager) {
    logger.info(manager.fullInfo()
    );
    managerService.updateManager(manager);
    return "redirect:list-managers";
  }

  @GetMapping("/deleteManager/{id}")
  public String deleteManager(@PathVariable("id")long id) {
    managerService.deleteManagerById(id);
    return "redirect:/personal/list-managers";
  }

  @GetMapping("/updateFactoryWorker/{id}")
  public String updateFactoryWorker(Model model, @PathVariable("id") long id) {
    FactoryWorker factoryWorker = factoryWorkerService.findFactoryWorkerById(id);
    model.addAttribute("factoryWorker", factoryWorker);
    return "views/personal/update-factoryWorker";
  }

  @PostMapping("/updateFactoryWorker")
  public String editFactoryWorker(FactoryWorker factoryWorker) {
    factoryWorkerService.updateFactoryWorker(factoryWorker);
    return "redirect:list-factoryWorkers";
  }

  @GetMapping("/deleteFactoryWorker/{id}")
  public String deleteFactoryWorker(@PathVariable("id")long id) {
    factoryWorkerService.deleteFactoryWorkerById(id);
    return "redirect:/personal/list-factoryWorkers";
  }

  @GetMapping("/updateStoreWorker/{id}")
  public String updateStoreWorker(Model model, @PathVariable("id") long id) {
    StoreWorker storeWorker = storeWorkerService.findStoreWorkerById(id);
    model.addAttribute("storeWorker", storeWorker);
    return "views/personal/update-storeWorker";
  }

  @PostMapping("/updateStoreWorker")
  public String editStoreWorker(StoreWorker storeWorker) {
    storeWorkerService.updateStoreWorker(storeWorker);
    return "redirect:list-storeWorkers";
  }

  @GetMapping("/deleteStoreWorker/{id}")
  public String deleteStoreWorker(@PathVariable("id")long id) {
    storeWorkerService.deleteStoreWorkerById(id);
    return "redirect:/personal/list-storeWorkers";
  }
}
