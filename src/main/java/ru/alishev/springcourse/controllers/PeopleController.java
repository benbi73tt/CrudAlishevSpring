package ru.alishev.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        //получение всех людей из DAO и передади на отображение и представлени
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        //получение одного человека по id из DAO и передадим на отображение и представлени

        model.addAttribute("person", personDAO.show(id));
        return "people/show";

    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person){ //(Model model)
//        model.addAttribute("person" ,new Person());
        return "people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") Person person){
        personDAO.save(person);
        return "redirect:/people"; //редирект
    }

     @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id){//извлечь id с помощью pathVariable
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
     }

     @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id){
        personDAO.update(id,person);
        return "redirect:/people";
     }

     @DeleteMapping("${id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";

     }













}
