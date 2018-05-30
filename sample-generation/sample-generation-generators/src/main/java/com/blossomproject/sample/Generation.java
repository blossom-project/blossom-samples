package com.blossomproject.sample;

import com.blossomproject.generator.configuration.GeneratorBuilder;

import javax.persistence.TemporalType;
import java.io.File;

public class Generation {
  public static void main(String[] argv) throws Exception {
    GeneratorBuilder builder = GeneratorBuilder.create();


    builder
        .settings()
        .basePackage("com.blossomproject.sample.generated")
        .projectRoot(new File("./sample-generation/sample-generation-application/").getAbsolutePath())
        .entityName("SampleEntity")
        .fields()
        .defaultFields()
        ._bigDecimal("bigDecimalField", 5, 5)
        .and()._blob("blobField")
        .and()._boolean("booleanField")
        .and()._date("dateField", TemporalType.TIMESTAMP)
        .and()._enum("enumField", EnumFieldValues.class)
        .and()._integer("integerField")
        .and()._localDateTime("localDateTimeField", TemporalType.TIMESTAMP)
        .and()._long("longField")
        .and()._string("stringField").maxLength(255)
    ;

    builder.executionPlan().allClasses().allResources();
    builder.build().generate();
  }
}
