package com.ak.shortURL.controller;

import com.ak.shortURL.model.ShortLinkModel;
import com.ak.shortURL.service.ShortUrlService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Controller {

    @Autowired
    ShortUrlService shortUrlService;

    //POST endpoint
    @RequestMapping(method = RequestMethod.POST, value = "/getUrl")
    public String createShortUrl(@RequestBody String longUrl){
        String url = shortUrlService.createShortUrl(longUrl);
        return url;
    }

    //check if the short url already exists
    @RequestMapping(method = RequestMethod.GET, value ="/getShortUrl/{shortUrl}")
    public String getShortUrl(@PathVariable("shortUrl") String shortUrl){
        ShortLinkModel shortLinkModel = shortUrlService.getLongUrl(shortUrl);
        if(shortLinkModel == null){
            return "Not available, try creating short link first!!!";
        }
        return "https://mini.com/" + shortLinkModel.getShortUrl();
    }


    // This should redirect to the actual link if the provided short link is valid.
    @RequestMapping(method = RequestMethod.GET, value = "/getLongUrl/{shortUrl}")
    public String getLongUrl(@PathVariable("shortUrl") String shortUrl){
        ShortLinkModel shortLinkModel = shortUrlService.getLongUrl(shortUrl);
        if(shortLinkModel == null){
            return "This Url is new, not present in DB, try creating short link..!!!";
        }
        
        return shortLinkModel.getLongUrl();
    }



    @RequestMapping(method = RequestMethod.GET, value ="/goToLongUrl/{shortUrl}")
    public RedirectView redirectWithUsingRedirectView(@PathVariable("shortUrl") String shortUrl) {
        ShortLinkModel shortLinkModel = shortUrlService.getLongUrl(shortUrl);
        if(shortLinkModel == null){
            return new RedirectView("/getLongUrl/{shortUrl}");
        }

        return new RedirectView(shortLinkModel.getLongUrl());
        }


}
