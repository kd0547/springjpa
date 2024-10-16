package org.example.springweb1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        //model에 데이터를 넣어서 view에 넘길 수 있음

        model.addAttribute("data","hello!!!");

        return "hello";
    }
}
