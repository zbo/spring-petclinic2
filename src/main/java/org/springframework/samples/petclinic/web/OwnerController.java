/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
public class OwnerController {

    private final ClinicService clinicService;

    @Autowired
    public OwnerController(ClinicService clinicService) {
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


    @RequestMapping(value = "/owners/new", method = RequestMethod.GET)
    public String initCreationForm(Map<String, Object> model) {
        Owner owner = new Owner();
        model.put("owner", owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @RequestMapping(value = "/owners/new", method = RequestMethod.POST)
    public String processCreationForm(@Valid Owner owner, BindingResult result) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @RequestMapping(value = "/owners/find", method = RequestMethod.GET)
    public String initFindForm(Map<String, Object> model) {
        model.put("owner", new Owner());
        return "owners/findOwners";
    }

    @RequestMapping(value = "/owners-firstName", method = RequestMethod.GET)
    public String processFindForm2(Owner owner, BindingResult result, Map<String, Object> model) {
        if (owner.getFirstName() == null) {
            owner.setFirstName(""); // empty string signifies broadest possible search
        }
        Collection<Owner> results = this.clinicService.findOwnerByFirstName(owner.getFirstName());
        return routeToPage(result, model, results);
    }

    @RequestMapping(value = "/owners-lastName", method = RequestMethod.GET)
    public String processFindForm(Owner owner, BindingResult result, Map<String, Object> model) {

        // allow parameterless GET request for /owners to return all records
        if (owner.getLastName() == null) {
            owner.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        Collection<Owner> results = this.clinicService.findOwnerByLastName(owner.getLastName());
        return routeToPage(result, model, results);
    }

    private String routeToPage(BindingResult result, Map<String, Object> model, Collection<Owner> results) {
        Owner owner;
        if (results.isEmpty()) {
            // no owners found
            result.rejectValue("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            owner = results.iterator().next();
            return "redirect:/owners/" + owner.getId();
        } else {
            // multiple owners found
            model.put("selections", results);
            return "owners/ownersList";
        }
    }

    @RequestMapping(value = "/owners/{ownerId}/edit", method = RequestMethod.GET)
    public String initUpdateOwnerForm(@PathVariable("ownerId") int ownerId, Model model) {
        Owner owner = this.clinicService.findOwnerById(ownerId);
        model.addAttribute(owner);
        return "owners/createOrUpdateOwnerForm";
    }

    @RequestMapping(value = "/owners/{ownerId}/edit", method = RequestMethod.POST)
    public String processUpdateOwnerForm(@Valid Owner owner, BindingResult result, @PathVariable("ownerId") int ownerId) {
        if (result.hasErrors()) {
            return "owners/createOrUpdateOwnerForm";
        } else {
            owner.setId(ownerId);
            this.clinicService.saveOwner(owner);
            return "redirect:/owners/{ownerId}";
        }
    }

    /**
     * Custom handler for displaying an owner.
     *
     * @param ownerId the ID of the owner to display
     * @return a ModelMap with the model attributes for the view
     */
    @RequestMapping("/owners/{ownerId}")
    public ModelAndView showOwner(@PathVariable("ownerId") int ownerId) {
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(this.clinicService.findOwnerById(ownerId));
        return mav;
    }

    @RequestMapping("/owners.json")
    public
    @ResponseBody
    void showResourcesOwnerList() throws IOException {
        Owners ownerList = new Owners();
        ownerList.getOwnerList().addAll(this.clinicService.findOwners());
        this.response.setContentType("application/json;charset=UTF-8");
        this.response.getWriter().write((JsonUtil.toJson(ownerList)));
    }

    @RequestMapping("/owners_and_pets.json")
    public
    @ResponseBody
    Owners showResourcesOwnerPetList() {
        Owners owners = new Owners();
        owners.getOwnerList().addAll(this.clinicService.findOwnersAndPets());
        return owners;
    }

}
