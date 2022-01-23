package com.mybury.bucketlist.auth.config;

import java.util.List;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * fileName      : CacheConfig
 * date         : 2022/01/22
 * description  :
 */
@Configuration
public class CacheConfig {

  public static final String TOKEN_CACHE = "TOKEN_CACHE";

  @Bean
  public CacheManager cacheManager() {
    SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
    simpleCacheManager.setCaches(List.of(new ConcurrentMapCache(TOKEN_CACHE)));
    return simpleCacheManager;
  }
}
