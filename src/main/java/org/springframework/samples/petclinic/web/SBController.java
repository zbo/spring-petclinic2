package org.springframework.samples.petclinic.web;


import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.google.gson.Gson;
import com.mysql.fabric.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Owners;
import org.springframework.samples.petclinic.model.Vets;
import org.springframework.samples.petclinic.service.ClinicService;
import org.springframework.samples.petclinic.util.JsonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by zbo on 4/10/16.
 */
@Controller
public class SBController {
    private final ClinicService clinicService;

    @Autowired
    public SBController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }

    @RequestMapping(value = "bootstrap/index", method = RequestMethod.GET)
    public String init_index(Map<String, Object> model) {
        return "bootstrap/pages/index";
    }

    @RequestMapping(value = "bootstrap/owners", method = RequestMethod.GET)
    public String get_owner(Map<String, Object> model) {
        return "bootstrap/pages/owners";
    }

    @RequestMapping(value = "bootstrap/owners/{ownerId}", method = RequestMethod.GET)
    public ModelAndView show_owner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("bootstrap/pages/ownerDetails");
        mav.addObject(this.clinicService.findOwnerById(ownerId));
        return mav;
    }

    @RequestMapping(value = "bootstrap/owners/{ownerId}", method = RequestMethod.POST)
    public String edit_owner(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        owner.setId(ownerId);
        this.clinicService.saveOwner(owner);
        return "redirect:/bootstrap/owners";

    }
    @RequestMapping(value = "bootstrap/owners/new", method = RequestMethod.GET)
    public ModelAndView new_owner() {
        ModelAndView mav = new ModelAndView("bootstrap/pages/ownerDetails");
        Owner owner= new Owner();
        mav.addObject("owner", owner);
        return mav;
    }

    @RequestMapping(value = "bootstrap/owners/new", method = RequestMethod.POST)
    public String create_owner(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "bootstrap/pages/ownerDetails";
        } else {
            this.clinicService.saveOwner(owner);
            return "redirect:/bootstrap/owners/" + owner.getId();
        }
    }

    @RequestMapping(value = "bootstrap/owners/delete/{ownerId}", method = RequestMethod.GET)
    public String delete_owner(@PathVariable("ownerId") int ownerId) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        this.clinicService.deleteOwner(owner);
        return "redirect:/bootstrap/owners";

    }
}
