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

      log.info("Downloading build.gradle...");
      awsS3Service.download("aws-s3-services-starter", "build.gradle", "build");
      log.info("File downloaded.");

      log.info("Making a copy of build.gradle...");
      awsS3Service.copy("aws-s3-services-starter", "build.gradle", "recycle-this");
      log.info("Copied.");

      System.out.println("In aws-s3-services-starter bucket: " + awsS3Service.listFiles("aws-s3-services-starter"));
      System.out.println("In recycle-this bucket: " + awsS3Service.listFiles("recycle-this"));

      log.info("Deleting build.gradle...");
      awsS3Service.delete("aws-s3-services-starter", "build.gradle");
      awsS3Service.delete("recycle-this", "build.gradle");
      log.info("Deleted.");

      System.out.println("In aws-s3-services-starter bucket: " + awsS3Service.listFiles("aws-s3-services-starter"));
      System.out.println("In recycle-this bucket: " + awsS3Service.listFiles("recycle-this"));
    };
  }
}
