package com.example.myproject.controller;

import com.example.myproject.model.Part;
import com.example.myproject.repos.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class MyController {
    @Autowired
    private PartRepository partRepo;

    @GetMapping("/menu")
    public String begin(){
        return "menu";
    }

    @GetMapping(path = "/menu", params = "addPage")
    public String goToAdd(){
        return "redirect:/home";
    }

    @GetMapping(path = "/menu", params = "searchPage")
    public String goToSearch(){
        return "redirect:/search";
    }

    @GetMapping(path = "/search", params = "back_to_menu")
    public String goToMenuFromFilter(){
        return "redirect:/menu";
    }

    @GetMapping(path = "/home", params = "back_to_menu")
    public String goToMenuFromPage(){
        return "redirect:/menu";
    }

    @GetMapping(path = "/home")
    public String formAdd() {
        return "page";
    }

    @GetMapping(path = "/search")
    public String formFilter() {
        return "filter";
    }

    @PostMapping(path = "/home")
    public String addPart(@RequestParam String name, @RequestParam String partNumber, @RequestParam String manufacturer,
                          @RequestParam String forModel, @RequestParam double purchasePrice, @RequestParam double salePrice) {
        Part part = new Part(name, partNumber, manufacturer, forModel, purchasePrice, salePrice);

        partRepo.save(part);

        return "page";
    }

    @PostMapping(path = "/search")
    public String filter(@RequestParam String partNumber, Map<String, Object> model) {
        Iterable<Part> parts;
        if ((partNumber == null) || (partNumber.isEmpty())) {
            parts = partRepo.findAll();
        } else
            parts = partRepo.findByPartNumber(partNumber);

        model.put("parts", parts);

        return "filter";
    }
}
