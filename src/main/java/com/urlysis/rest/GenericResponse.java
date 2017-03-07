package com.urlysis.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by wrui on 3/7/17.
 */
public class GenericResponse {
   @JsonInclude(JsonInclude.Include.NON_EMPTY)
   String error;

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
}
