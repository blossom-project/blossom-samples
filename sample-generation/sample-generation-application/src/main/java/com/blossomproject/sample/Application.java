package com.blossomproject.sample;

import com.blossomproject.sample.generated.SampleEntity;
import com.blossomproject.sample.generated.SampleEntityDTO;
import com.blossomproject.sample.generated.SampleEntityService;
import org.apache.commons.lang3.ArrayUtils;
import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class Application {


  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public DataFactory df() {
    return new DataFactory();
  }

  @Bean
  public Random random() {
    return new Random();
  }

  @Bean
  public CommandLineRunner clr(SampleEntityService service, DataFactory df, Random random) {
    return args -> IntStream.range(0, 15).mapToObj(i -> {
      SampleEntityDTO entity = new SampleEntityDTO();
      entity.setBigDecimalField(BigDecimal.valueOf(random.nextDouble()));
      entity.setBlobField(ArrayUtils.toObject(df.getRandomChars(500).getBytes(StandardCharsets.UTF_8)));
      entity.setBooleanField(random.nextBoolean());
      entity.setDateField(df.getDateBetween(Date.from(Instant.now()), Date.from(Instant.now().plus(5, ChronoUnit.DAYS))));
      entity.setDescription(df.getRandomText(250));
      entity.setEnumField(random.nextBoolean() ? SampleEntity.EnumFieldValues.first : SampleEntity.EnumFieldValues.second);
      entity.setIntegerField(random.nextInt());
      entity.setLocalDateTimeField(df.getDateBetween(Date.from(Instant.now()), Date.from(Instant.now().plus(5, ChronoUnit.DAYS))));
      entity.setLongField(random.nextLong());
      entity.setName(df.getName());
      entity.setStringField(df.getRandomWord());
      return entity;
    }).forEach(service::create);
  }

}
