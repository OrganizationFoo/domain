package org.foo.sandbox.data.controller;

import javax.validation.Valid;

import org.foo.sandbox.data.model.User;
import org.foo.sandbox.data.model.dto.UserDto;
import org.foo.sandbox.data.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String list() {
        return "redirect:/user/list";
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save(@Valid @ModelAttribute("userForm") UserDto user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/edit";
        }
        try {
            user = userService.save(user);
            return "redirect:/user/list";
        } catch (Exception e) {
            e.printStackTrace();
            Throwable tmp = e;
            do {
                ObjectError error = new ObjectError(tmp.getClass().getSimpleName(), tmp.getMessage());
                bindingResult.addError(error);
            } while ((tmp = tmp.getCause()) != null);
            return "user/edit";
        }
    }

    @RequestMapping({ "/user/list", "/user" })
    public String list(Model model) {
        model.addAttribute("users", userService.findAll(0, 100, "email", Direction.ASC));
        return "user/list";
    }

    @RequestMapping("/user/new")
    public String create(Model model) {
        model.addAttribute("userForm", new UserDto());
        return "user/edit";
    }

    @RequestMapping("/user/show/{id}")
    public String find(@PathVariable String id, Model model) {
        model.addAttribute("user", userService.findById(Long.valueOf(id)));
        return "user/show";
    }

    @RequestMapping("user/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        User user = userService.findById(Long.valueOf(id));
        model.addAttribute("userForm", user);
        return "user/edit";
    }

    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable String id) {
        userService.deleteById(Long.valueOf(id));
        return "redirect:/user/list";
    }
}
