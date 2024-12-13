package com.ak.shortURL.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    //POST endpoint
    @PostMapping("/getUrl")
    public String createShortUrl(@RequestBody String LongUrl){

        return "Success";
    }

}
