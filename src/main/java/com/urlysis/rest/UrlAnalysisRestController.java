package com.urlysis.rest;

import com.urlysis.biz.UrlService;
import com.urlysis.model.UrlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by wrui on 3/7/17.
 */
@RestController
@RequestMapping("/api/v1")
public class UrlAnalysisRestController {
   @Autowired
   UrlService urlService;

   /**
    * Analyze an url and return related info.
    * It can consume a callbackUrl wrapped as JSON object, such as {"callbackUrl": "www.callme-maybe.com"}
    * Note that the mapping has a '/' at the end.
    */
   @RequestMapping(value = "/analyze",
                   method = RequestMethod.POST,
                   produces = "application/json; charset=UTF-8",
                   consumes = "text/plain")
   public ResponseEntity<GenericResponse> analyzeUrl(@RequestParam String url,
                                                     @RequestBody String callbackUrl){
      UrlInfo urlInfo = urlService.analyze(url, callbackUrl);

      if(!StringUtils.isEmpty(callbackUrl)){
         return new ResponseEntity<>(new GenericResponse(), HttpStatus.OK);
      }

      UrlInfoResponse response = new UrlInfoResponse();
      response.setUrlInfo(urlInfo);
      return new ResponseEntity<>(response, HttpStatus.OK);
   }

   /**
    * Get info for already analyzed url.
    */
   @RequestMapping(value = "/analyze", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
   public ResponseEntity<UrlInfoResponse> getInfoOf(@RequestParam String url){
      Optional<UrlInfo> urlInfo = urlService.getInfoOf(url);
      UrlInfoResponse response = new UrlInfoResponse();

      if (!urlInfo.isPresent()){
         response.setError("Couldn't find any analyzed information the url.");
         return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
      }
      response.setUrlInfo(urlInfo.get());
      return new ResponseEntity<>(response, HttpStatus.OK);
   }
}
