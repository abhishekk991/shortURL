package com.ak.shortURL.controller;

import com.ak.shortURL.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    ShortUrlService shortUrlService;

    //POST endpoint
    @PostMapping("/getUrl")
    public String createShortUrl(@RequestBody String longUrl){
        String url = shortUrlService.createShortUrl(longUrl);
        return url;
    }

}
