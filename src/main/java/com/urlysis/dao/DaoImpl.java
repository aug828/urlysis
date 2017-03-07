package com.urlysis.dao;

import com.urlysis.model.UrlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Created by wrui on 3/7/17.
 */
@Repository
public class DaoImpl implements Dao {
   JdbcTemplate template;

   @Autowired
   @Qualifier(value = "dataSource")
   public void setDataSource(DataSource dataSource){
      this.template = new JdbcTemplate(dataSource);
   }


   // TODO insert data into database
   @Override
   public boolean insert(UrlInfo urlInfo) {
      return false;
   }

   // TODO find data from database
   @Cacheable(cacheNames="urlInfo", key="#url")
   @Override
   public Optional<UrlInfo> find(String url) {
      return Optional.empty();
   }
}
