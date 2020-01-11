package com.pink.retail.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@Configuration
@EnableScheduling
public class ScheduleClass {
	
	@Autowired
	BliMailService mailService;

	@Scheduled(cron="0 30 9 * * *", zone="Europe/Sofia")
	public void scheduleFixedRateWithInitialDelayTask() {
	  
	   mailService.sendSuggestionsMail();
	}
}
