package ru.spbstu.antufievsemen.coursedatabase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.spbstu.antufievsemen.coursedatabase.entity.User;
import ru.spbstu.antufievsemen.coursedatabase.service.UserService;

@Controller
public class MenuController {

  private final UserService userService;

  public MenuController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("")
  public String showMainMenu() {
    return "views/menu/mainmenu";
  }

  @GetMapping("/updateUser")
  public String updateUser(Model model) {
    model.addAttribute("user", new User());
    return "views/menu/update-user";
  }

  @PostMapping("/updateUser")
  public String editUser(User userDao) {
    User user = userService.findUserByUsername(userDao.getUsername());
    user.setPassword(userDao.getPassword());
    userService.updateUser(user);
    return "redirect:/";
  }

}
