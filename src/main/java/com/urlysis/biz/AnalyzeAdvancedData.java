package com.urlysis.biz;

import com.urlysis.model.UrlInfo;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;

/**
 * Created by wrui on 3/7/17.
 */
public class AnalyzeAdvancedData implements Callable<UrlInfo> {

   String url;
   public AnalyzeAdvancedData(String url) {
      this.url = url;
   }

   @Override
   public UrlInfo call() throws Exception {
      RestTemplate restTemplate = new RestTemplate();
//      MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//      params.add("X-AYLIEN-TextAPI-Application-Key", "myappkey");
//      params.add("X-AYLIEN-TextAPI-Application-ID", "myappid");
      return restTemplate.getForObject("https://api.aylien.com/api/v1/sentiment?url="+url,  UrlInfo.class);
   }
}
