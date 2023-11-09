package com.festoi.festoi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/festoi/upload-art")
public class uploadArtController {

    @GetMapping
    public ModelAndView getUploadArtPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("addArt");
        System.out.println(modelAndView.getViewName());
        return modelAndView;
    }
}
