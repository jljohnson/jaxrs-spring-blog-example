package com.stormpath.example.springboot.controllers;

import com.stormpath.example.common.dao.StormtrooperDao;
import com.stormpath.example.common.model.Stormtrooper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collection;

@Controller
@RequestMapping("/troopers")
public class StormtroooperController {

    @Autowired
    private StormtrooperDao trooperDao;

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Collection<Stormtrooper> listTroopers() {
        return trooperDao.listStormtroopers();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Stormtrooper getTrooper(@PathVariable("id") String id) throws NotFoundException {

        Stormtrooper stormtrooper = trooperDao.getStormtrooper(id);
        if (stormtrooper == null) {
            throw new NotFoundException();
        }
        return stormtrooper;
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody Stormtrooper createTrooper(@RequestBody Stormtrooper trooper) {

        return trooperDao.addStormtrooper(trooper);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public @ResponseBody Stormtrooper updateTrooper(@PathVariable("id") String id, @RequestBody Stormtrooper updatedTrooper) throws NotFoundException {

        return trooperDao.updateStormtrooper(id, updatedTrooper);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTrooper(@PathVariable("id") String id) {
        trooperDao.deleteStormtrooper(id);
    }


}
