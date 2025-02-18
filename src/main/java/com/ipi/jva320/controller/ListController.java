package com.ipi.jva320.controller;

import com.ipi.jva320.model.SalarieAideADomicile;
import com.ipi.jva320.service.SalarieAideADomicileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ListController {

    @Autowired
    private SalarieAideADomicileService salarieAideADomicileService;

    @GetMapping("/salaries")
    public String list(@RequestParam(value = "matricule",required = false) String matricule,
                       @RequestParam(value = "page", required = false) Integer page,
                       @RequestParam(value = "size", required = false) Integer size,
                       @RequestParam(value = "sortProperty",required = false) String sortProperty,
                       @RequestParam(value = "sortDirection", required = false) String sortDirection, ModelMap model) {
        if(matricule != null) {
            model.addAttribute("salaries", salarieAideADomicileService.getSalaries(matricule));
            model.addAttribute("sortDirection", sortDirection);
            model.put("employeeAmount",salarieAideADomicileService.countSalaries());
            return "list";
        }
        else if(page == null || size == null) {
            model.addAttribute("salaries", salarieAideADomicileService.getSalaries());
            model.addAttribute("sortDirection", sortDirection);
            model.put("employeeAmount",salarieAideADomicileService.countSalaries());
            return "list";
        }
        else {
            model.addAttribute("sortDirection", sortDirection);
            Sort sort = null;
            if(sortDirection.equals("ASC")) {
                sort = Sort.by(sortProperty).ascending();
            }
            else if(sortDirection.equals("DESC")) {
                sort = Sort.by(sortProperty).descending();
            }
            model.addAttribute("salaries", salarieAideADomicileService.getSalaries(PageRequest.of(page,size,sort)));

            model.put("employeeAmount",salarieAideADomicileService.countSalaries());
            return "list";
        }
    }
}
