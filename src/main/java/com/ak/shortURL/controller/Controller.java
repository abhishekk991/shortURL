package com.ak.shortURL.controller;

import com.ak.shortURL.model.ShortLinkModel;
import com.ak.shortURL.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/getShortUrl")
    public String getShortUrl(@RequestBody String shortUrl){
        ShortLinkModel shortLinkModel = shortUrlService.getLongUrl(shortUrl);
        if(shortLinkModel == null){
            return "Not available, try creating short link first!!!";
        }
        return shortLinkModel.getShortUrl();
    }

    @GetMapping("/getLongUrl")
    public String getLongUrl(@RequestBody String shortUrl){
        ShortLinkModel shortLinkModel = shortUrlService.getLongUrl(shortUrl);
        if(shortLinkModel == null){
            return "This Url is new, not present in DB, try creating short link..!!!";
        }
        return shortLinkModel.getLongUrl();
    }

}
