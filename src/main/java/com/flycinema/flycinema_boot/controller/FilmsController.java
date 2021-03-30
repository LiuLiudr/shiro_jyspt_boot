package com.flycinema.flycinema_boot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author liudingrong
 */
@Controller
@RequestMapping("/films")
public class FilmsController {
    @Autowired


    @RequestMapping("/findHottestFilms")
    public String findHottestFilms() {

        return "html/mypage";
    }

}
