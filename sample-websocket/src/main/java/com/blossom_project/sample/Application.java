
package com.blossom_project.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@SpringBootApplication
public class Application {

  private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public final static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

  @Configuration
  @EnableWebSocket
  public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(myHandler(), "/echo");
    }

    @Bean
    public EchoSocketHandler myHandler() {
      return new EchoSocketHandler();
    }
  }
}
