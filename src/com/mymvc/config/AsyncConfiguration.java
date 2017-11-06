package com.mymvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by alan.luo on 2017/9/24.
 */
@Configuration
@EnableAsync(proxyTargetClass = true)
@EnableScheduling
public class AsyncConfiguration {
}
