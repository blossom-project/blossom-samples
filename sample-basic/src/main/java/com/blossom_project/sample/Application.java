
package com.blossom_project.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

  private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public final static void main(String... args) {
    ApplicationContext ctx = SpringApplication.run(Application.class, args);
    String[] definitions = ctx.getBeanDefinitionNames();
    for (String definition : definitions) {
      LOGGER.info("Bean {} of type {}", definition, ctx.getBean(definition).getClass());
    }
  }

}
