package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import java.sql.SQLException;

@Controller
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()   //показывает всех юзеров, по умолчанию
    public String showUsers(Model model) throws SQLException {
        model.addAttribute("showUsers", userServiceImpl.showUsers());
    return "users";
    }

    @GetMapping("/show/{id}")   //показывает детали одного юзера
    public String showUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("showUser", userServiceImpl.showUser(id));
        return "show";
    }

    @GetMapping("/new") //создание нового юзера
    public String newUserForm(Model model) {
        model.addAttribute("newUserForm", new User());
        return "new";
    }

    @PostMapping(value = "/new")    //сохранение нового юзера и показ всех юзеров
    public String newUser(@ModelAttribute("newUser")User user) throws SQLException {
        userServiceImpl.save(user);
        return "redirect:/";
    }

    @GetMapping("/update/{id}") //форма апдейта юзера
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("updateUser", userServiceImpl.showUser(id));
        return("update");
    }

    @PatchMapping("/update/{id}")   //апдейт юзера и показ всех юзеров
    public String update(@ModelAttribute("updatedUser") User user, @PathVariable("id") int id) {
        userServiceImpl.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/show/{id}")    //удаление юзера
    public String deleteUser(@PathVariable("id") int id) {
        userServiceImpl.delete(id);
        return "redirect:/";
    }
}
