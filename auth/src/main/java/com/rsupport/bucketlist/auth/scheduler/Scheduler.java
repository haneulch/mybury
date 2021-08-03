package com.rsupport.bucketlist.auth.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {
	@Scheduled(cron = "0 0 1 * * ?")
	public void excuteGC() {
		Runtime.getRuntime().gc();
	}
}
