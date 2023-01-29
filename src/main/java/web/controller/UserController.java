package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @GetMapping()   //показывает всех юзеров, по умолчанию
    public String showUsers(Model model) throws SQLException {
        model.addAttribute("showUsers", userService.showUsers());
    return "users";
    }

    @GetMapping("/show/{id}")   //показывает детали одного юзера
    public String showUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("showUser", userService.showUser(id));
        return "show";
    }

    @GetMapping("/new") //создание нового юзера
    public String newUserForm(Model model) {
        model.addAttribute("newUserForm", new User());
        return "new";
    }

    @PostMapping(value = "/new")    //сохранение нового юзера и показ всех юзеров
    public String newUser(@ModelAttribute("newUser")User user) throws SQLException {
        userService.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}") //форма апдейта юзера
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("updateUser", userService.showUser(id));
        return("update");
    }

    @PatchMapping("/update/{id}")   //апдейт юзера и показ всех юзеров
    public String update(@ModelAttribute("updatedUser") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/show/{id}")    //удаление юзера
    public String deleteUser(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/";
    }

//    @PostMapping()
//    public String newUser(@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name, @RequestParam("email") String email, Model model) {
//        User user = new User();
//        user.setFirst_name(first_name);
//        user.setLast_name(last_name);
//        user.setEmail(email);
//
//        model.addAttribute("newUser", user);
//        return "created";
//    }

//    @GetMapping("/{id}")
//    public String showUser(@PathVariable("id") int id, Model model) {
//        model.addAttribute("userInfo", userService.showUser(id));
//        return "/showUser";
//    }

}
