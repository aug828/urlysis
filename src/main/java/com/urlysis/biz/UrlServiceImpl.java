package com.urlysis.biz;

import com.urlysis.dao.Dao;
import com.urlysis.model.UrlInfo;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.concurrent.*;

/**
 * Created by wrui on 3/7/17.
 */
@Service
public class UrlServiceImpl implements UrlService {

   @Autowired
   Dao dao;

   @Override
   public UrlInfo analyze(String url, String callbackUrl) {
      Optional<UrlInfo> urlInfo = dao.find(url);

      UrlInfo infoToBeSaved = null;
      // if can't find existing url
      if(!urlInfo.isPresent()){
         AnalyzeBasicData basicData = new AnalyzeBasicData(url);
         AnalyzeAdvancedData advancedData = new AnalyzeAdvancedData(url);

         ExecutorService executor = Executors.newFixedThreadPool(2);
         CompletionService<UrlInfo> completion = new ExecutorCompletionService<>(executor);

         completion.submit(basicData);
         completion.submit(advancedData);

         executor.shutdown();

         UrlInfo urlInfo1 = null;
         UrlInfo urlInfo2 = null;
         try {
            urlInfo1 = completion.take().get();
            urlInfo2 = completion.take().get();
         } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
         }

         // merge urlInfo1 and urlInfo2
         infoToBeSaved = merge(urlInfo1, urlInfo2);
         dao.insert(infoToBeSaved);
      }else{
         infoToBeSaved = urlInfo.get();
      }

      if(!StringUtils.isEmpty(callbackUrl)){
         // the requirement didn't see if the callbackUrl is rest service or not.
         // Here we assume it's a rest service
         RestTemplate restTemplate = new RestTemplate();
         restTemplate.postForLocation(callbackUrl, infoToBeSaved);
      }

      return infoToBeSaved;
   }

   @Override
   public Optional<UrlInfo> getInfoOf(String url) {
      return dao.find(url);
   }

   private UrlInfo merge(UrlInfo info1, UrlInfo info2){
      info1.setTitle(info1.getTitle() != null ? info1.getTitle() : info2.getTitle());
      info1.setAuthor(info1.getAuthor() != null ? info1.getAuthor() : info2.getAuthor());
      info1.setDesc(info1.getDesc() != null ? info1.getDesc() : info2.getDesc());
      info1.setDate(new DateTime());
      info1.setPolarity(info1.getPolarity() != null ? info1.getPolarity() : info2.getPolarity());
      info1.setPolarityConfidence(info1.getPolarityConfidence() != 0 ? info1.getPolarityConfidence() : info2.getPolarityConfidence());
      info1.setSubjectivity(info1.getSubjectivity() != null ? info1.getSubjectivity() : info2.getSubjectivity());
      info1.setSubjectivityConfidence(info1.getSubjectivityConfidence() != 0 ? info1.getSubjectivityConfidence() : info2.getSubjectivityConfidence());

      return info1;
   }
}
