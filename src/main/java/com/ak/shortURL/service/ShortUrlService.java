package com.ak.shortURL.service;

import com.ak.shortURL.model.ShortLinkModel;
import com.ak.shortURL.repository.ShortLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class ShortUrlService {

    private static final String BASE62_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*|~";

    @Autowired
    ShortLinkRepository shortLinkRepository;


    public String createShortUrl(String longUrl) {
        if(chekIfLongUrlAlreadyPresentInDB(longUrl) != null){
            ShortLinkModel shortLinkModel = chekIfLongUrlAlreadyPresentInDB(longUrl);
            return shortLinkModel.getShortUrl();
        }
        String sha256Value = convertToSHA256(longUrl);
        String shortUrl = getBase62Value(sha256Value);
        saveUrl(sha256Value,shortUrl, longUrl);
        return "https://mini.com/" + shortUrl;
    }

    public ShortLinkModel getShortUrl(String longUrl){
        return chekIfLongUrlAlreadyPresentInDB(longUrl);
    }

    public ShortLinkModel getLongUrl(String shortUrl){
        return chekIfShortUrlAlreadyPresentInDB(shortUrl);
    }

    private ShortLinkModel chekIfShortUrlAlreadyPresentInDB(String shortUrl){
        Optional<ShortLinkModel> entity = shortLinkRepository.findByShortUrl(shortUrl);
        return entity.orElse(null);
    }

    private ShortLinkModel chekIfLongUrlAlreadyPresentInDB(String longUrl){
        Optional<ShortLinkModel> entity = shortLinkRepository.findByLongUrl(longUrl);
        return entity.orElse(null);
    }

    public String getBase62Value(String lastId){

        long numericValue = 0;
        for (int i = 0; i < lastId.length(); i++) {
            numericValue = numericValue * 256 + (int) lastId.charAt(i); // Convert char to ASCII
        }

        if (numericValue == 0) {
            return "0";
        }

        StringBuilder result = new StringBuilder();

        while (numericValue > 0) {
            int remainder = (int) (numericValue % 72);
            result.append(BASE62_CHARS.charAt(remainder)); // Get corresponding character
            numericValue /= 72; // Integer division
        }

        return result.reverse().toString();

    }


    public String convertToSHA256(String input){
        // Get the SHA-256 message digest instance
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Convert the input string to bytes
        byte[] encodedhash = digest.digest(input.getBytes());

        // Convert the byte array to a hexadecimal string
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedhash) {
            hexString.append(String.format("%02x", b));  // Format as hexadecimal
        }

        return hexString.toString();
    }

    private void saveUrl(String sha256Value, String shortUrl, String longUrl){
        ShortLinkModel shortLinkModel = new ShortLinkModel();
        shortLinkModel.setSha256Value(sha256Value);
        shortLinkModel.setShortUrl(shortUrl);
        shortLinkModel.setLongUrl(longUrl);
        shortLinkRepository.save(shortLinkModel);
    }
}
