package com.suntecx.testspringSQS.sunil;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class SqsConfig {

        @Value("${cloud.aws.region.static}")
        private String region;

        @Value("${cloud.aws.credentials.access-key}")
        private String accessKey;

        @Value("${cloud.aws.credentials.secret-key}")
        private String secretKey;

        @Bean
        public QueueMessagingTemplate queueMessagingTemplate() {
            return new QueueMessagingTemplate(amazonSQSAsync());
        }

//        @Primary
//        @Bean
        public AmazonSQSAsync amazonSQSAsync() {
            System.out.println("****************** AmazonSqs is called WITH region"+region);
            AmazonSQSAsyncClientBuilder amazonSQSAsyncClientBuilder = AmazonSQSAsyncClientBuilder.standard();
            AmazonSQSAsync amazonSQSAsync = null;
            amazonSQSAsyncClientBuilder.withRegion(region);
            BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
            amazonSQSAsyncClientBuilder.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials));
            amazonSQSAsync = amazonSQSAsyncClientBuilder.build();

            System.out.println("****************** AmazonSqs is called");

            return amazonSQSAsync;

        }

    }

