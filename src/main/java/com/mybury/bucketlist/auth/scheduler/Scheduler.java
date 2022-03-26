package com.mybury.bucketlist.auth.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class Scheduler {
	@Scheduled(cron = "0 0 1 * * ?")
	public void excuteGC() {
		log.debug("============== GC EXCUTED ==============");
		Runtime.getRuntime().gc();
	}
}
