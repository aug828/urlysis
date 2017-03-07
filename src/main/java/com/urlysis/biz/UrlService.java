package com.urlysis.biz;

import com.urlysis.model.UrlInfo;

import java.util.Optional;

/**
 * Created by wrui on 3/7/17.
 */
public interface UrlService {
   UrlInfo analyze(String url, String callBackUrl);
   Optional<UrlInfo> getInfoOf(String url);
}
