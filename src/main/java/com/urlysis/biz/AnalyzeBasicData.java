package com.urlysis.biz;

import com.urlysis.model.UrlInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.concurrent.Callable;

/**
 * Created by wrui on 3/7/17.
 */
public class AnalyzeBasicData implements Callable<UrlInfo>{

   String url;
   public AnalyzeBasicData(String url) {
      this.url = url;
   }

   @Override
   public UrlInfo call() throws Exception {
      UrlInfo urlInfo = new UrlInfo();

      Document doc = Jsoup.connect(url).get();

      String title = doc.title();
      urlInfo.setTitle(title);

      Elements authorSelect = doc.select("meta[name=author]");
      if(authorSelect != null && authorSelect.size() > 0) {
         String author = authorSelect.first().attr("content");
         urlInfo.setAuthor(author);
      }

      Elements descSelect = doc.select("meta[name=description]");
      if(descSelect != null && descSelect.size() > 0) {
         String description = descSelect.first().attr("content");
         urlInfo.setDesc(description);
      }

      return urlInfo;
   }
}
