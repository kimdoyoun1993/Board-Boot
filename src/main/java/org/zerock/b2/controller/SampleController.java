package org.zerock.b2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/hello")
    public void hello(Model model){

        model.addAttribute("msg","반갑습니다.");
        log.info("----------------hello");
    }

    @GetMapping("/ex1")
    public void ex1(Model model){

        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");

        model.addAttribute("list",list);
    }

    @GetMapping("/ex3")
    public void ex3(Model model){

        List<String> list = Arrays.asList("AAA","BBB","CCC","DDD");

        model.addAttribute("list",list);
    }
}


