
package com.blossomproject.sample;

import com.blossomproject.ui.theme.Theme;
import com.blossomproject.ui.theme.ThemeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  private final static Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public final static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }


  @Bean
  public Theme purpleTheme(ThemeBuilder themeBuilder) {
    return themeBuilder
      .name("Purple")
      .scss().variables()
      ._primary("purple")
      ._navHeaderBg("url('/bg-purple.png') no-repeat")
      .done().done()
      .bodyClass("md-skin")
      .logo("/blossom/public/img/blossom-flower.svg")
      .build();
  }

  @Bean
  public Theme orangeTheme(ThemeBuilder themeBuilder) {
    return themeBuilder
      .name("Orange")
      .scss()
      .variables()
      ._primary("orange")
      ._warning("peru")
      ._navHeaderBg("url('/bg-orange.jpg') no-repeat")
      .done().done()
      .bodyClass("md-skin")
      .logo("/blossom/public/img/blossom-flower.svg")
      .build();
  }

  @Bean
  public Theme blueTheme(ThemeBuilder themeBuilder) {
    return themeBuilder
      .name("Blue")
      .scss()
      .additionnalScss("h1, h2, h3, h4, h5{font-decoration:underline;}")
      .variables()
      ._primary("#033372")
      ._navHeaderBg("url('/bg-blue.jpg') no-repeat")
      .done().done()
      .bodyClass("md-skin")
      .logo("/blossom/public/img/blossom-flower.svg")
      .build();
  }
}
