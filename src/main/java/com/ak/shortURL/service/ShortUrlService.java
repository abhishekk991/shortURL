package com.ak.shortURL.service;

import com.ak.shortURL.model.ShortLinkModel;
import com.ak.shortURL.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShortUrlService {

    @Autowired
    ShortLinkRepository shortLinkRepository;

//    @Autowired
//    ShortLinkModel shortLinkModel;

    public String createShortUrl(String longUrl){
        String result = "Success";
        saveUrl(result, longUrl);
        return result;
    }

    private void saveUrl(String sortUrl, String longUrl){
        ShortLinkModel shortLinkModel = new ShortLinkModel();
        shortLinkModel.setShort_url(sortUrl);
        shortLinkModel.setLong_Url(longUrl);
        shortLinkRepository.save(shortLinkModel);
    }
}
