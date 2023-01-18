package net.ukr.yougetit.demo;

import java.nio.file.Paths;
import net.ukr.yougetit.service.AwsS3Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AwsS3ServicesStarterDemoApplication {

  private final Logger log = LoggerFactory.getLogger(AwsS3ServicesStarterDemoApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(AwsS3ServicesStarterDemoApplication.class, args);
  }


  @Bean
  CommandLineRunner commandLineRunner(AwsS3Service awsS3Service) {
    return (String[] args) -> {
      log.info("Uploading build.gradle...");
      awsS3Service.upload("aws-s3-services-starter", Paths.get("build.gradle"));
      log.info("File uploaded.");
    };
  }
}
