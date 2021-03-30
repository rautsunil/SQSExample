package com.suntecx.testspringSQS.sunil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.aws.autoconfigure.context.ContextInstanceDataAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextRegionProviderAutoConfiguration;
import org.springframework.cloud.aws.autoconfigure.context.ContextStackAutoConfiguration;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = {ContextInstanceDataAutoConfiguration.class, ContextStackAutoConfiguration.class, ContextRegionProviderAutoConfiguration.class})

public class SunilApplication {
	public final   String QUEUE_NAME = "TestQueue";
	private static final Logger logger = LoggerFactory.getLogger(SunilApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SunilApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@SqsListener(value = "https://sqs.us-east-1.amazonaws.com/XXXXXXXXX/TestQueue")
	private void receive_message (String str){
		System.out.println("Str"+str);
		logger.debug("^^^^^STR MESSAGE FROM SQS",str);
	}
}

