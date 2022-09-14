package org.example.controller;

import org.example.dao.UserDAO;
import org.example.exceptions.FirstDataException;
import org.example.models.User;
import org.example.utils.ChekAge;
import org.example.utils.SerchData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;



@Controller
public class UserController {
    private UserDAO userDao;

    @Autowired
    public UserController(UserDAO userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String index(Model model)  {
        model.addAttribute("users", userDao.getAllUsers());
        return "/index";
    }

    @GetMapping("/hello")
    public String helloException()throws FirstDataException{
        throw new FirstDataException();
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDao.show(id));
        return "/show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        ChekAge chekAge = new ChekAge();
        if (bindingResult.hasErrors() || chekAge.chek(user.getYear(), user.getMonth(), user.getDay())){
            return "/new";
        }
        userDao.save(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDao.show(id));
        return "/update";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") @Valid User user,BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/update";
        }
        userDao.update(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDao.delete(id);
        return "redirect:/";
    }

    @PostMapping("/data")
    public ResponseEntity<User> showdata (@RequestBody SerchData data)throws Exception{
        SerchData serchData = new SerchData();
        if(!serchData.chekData(data.getDate1(), data.getData2())){
            throw new FirstDataException();}
        else {
            User result = userDao.showBetweenYear(data.getDate1(), data.getData2());
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
