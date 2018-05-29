package com.blinov.agileengine.controller;


import com.blinov.agileengine.cache.TransactionCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @Autowired private TransactionCacheManager cacheManager;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(ModelMap model) {

        model.addAttribute("transactions", cacheManager.getTransactions());
        return "index";
    }
}
