package com.ipi.jva320.controller;

import com.ipi.jva320.exception.SalarieException;
import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SalarieController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries/{id}")
    public String salaire(@PathVariable Long id,final ModelMap model) {
        SalarieAideADomicile salarie = salarieAideADomicileService.getSalarie(id);
        model.addAttribute("salarie",salarie);
        return "detail_Salarie";
    }
    @GetMapping("/salaries/aide/new")
    public String createsalaire(final ModelMap model) {
        SalarieAideADomicile salarie = new SalarieAideADomicile();
        model.addAttribute("salarie",salarie);
        return "detail_Salarie";
    }

    @PostMapping("/salaries")
    public String saveSalarie(@ModelAttribute SalarieAideADomicile salarie) {
        try {
            salarieAideADomicileService.creerSalarieAideADomicile(salarie);
        }catch (SalarieException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/salaries/";
    }




}
