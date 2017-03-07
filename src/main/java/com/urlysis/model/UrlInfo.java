package com.urlysis.model;

import org.joda.time.DateTime;

/**
 * Created by wrui on 3/7/17.
 */
public class UrlInfo {
   String url;

   String title;

   String desc;

   String author;

   DateTime date;

   String polarity;

   double polarityConfidence;

   String subjectivity;

   double subjectivityConfidence;

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }

   public String getAuthor() {
      return author;
   }

   public void setAuthor(String author) {
      this.author = author;
   }

   public DateTime getDate() {
      return date;
   }

   public void setDate(DateTime date) {
      this.date = date;
   }

   public String getPolarity() {
      return polarity;
   }

   public void setPolarity(String polarity) {
      this.polarity = polarity;
   }

   public double getPolarityConfidence() {
      return polarityConfidence;
   }

   public void setPolarityConfidence(double polarityConfidence) {
      this.polarityConfidence = polarityConfidence;
   }

   public String getSubjectivity() {
      return subjectivity;
   }

   public void setSubjectivity(String subjectivity) {
      this.subjectivity = subjectivity;
   }

   public double getSubjectivityConfidence() {
      return subjectivityConfidence;
   }

   public void setSubjectivityConfidence(double subjectivityConfidence) {
      this.subjectivityConfidence = subjectivityConfidence;
   }
}
