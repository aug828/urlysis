package com.urlysis.dao;

import com.urlysis.model.UrlInfo;

import java.util.Optional;

/**
 * Created by wrui on 3/7/17.
 */
public interface Dao {
   boolean insert(UrlInfo urlInfo);
   Optional<UrlInfo> find(String url);
}

