package vitfed.kata_spr_boot_security_pp_3_1_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vitfed.kata_spr_boot_security_pp_3_1_3.models.User;
import vitfed.kata_spr_boot_security_pp_3_1_3.service.RoleService;
import vitfed.kata_spr_boot_security_pp_3_1_3.service.UserService;
import vitfed.kata_spr_boot_security_pp_3_1_3.util.UserValidator;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@Controller
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;
    private  final UserValidator userValidator;
    @Autowired
    public AdminController(UserService userService, RoleService roleService, UserValidator userValidator) {
        this.userService = userService;
        this.roleService = roleService;
        this.userValidator = userValidator;
    }
    @GetMapping("/admin")
    public String findAll(Model model, Principal principal) {
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
        model.addAttribute("newUser", new User());
        model.addAttribute("allRoles", roleService.getAllRoles());
        return "table";
    }

    @PostMapping("/saveUser") //сохранение
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/admin";
        } else {
            userService.addUser(user);
            return "redirect:/admin";
        }
    }


    @PatchMapping(value = "/admin/{id}")//редактирование
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}/delete") //удаление
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
