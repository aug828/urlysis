package com.urlysis.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.urlysis.model.UrlInfo;

/**
 * Created by wrui on 3/7/17.
 */
public class UrlInfoResponse extends GenericResponse {
   @JsonInclude(JsonInclude.Include.NON_NULL)
   UrlInfo urlInfo;

   public UrlInfo getUrlInfo() {
      return urlInfo;
   }

   public void setUrlInfo(UrlInfo urlInfo) {
      this.urlInfo = urlInfo;
   }
}
