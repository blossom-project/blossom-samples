package com.blossomproject.sample.generated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SampleEntityCreateForm {
    @NotNull(message = "{sample_entitys.sample_entity.validation.name.NotNull.message}")
    @Size(max = 50)
    private String name;
    @NotNull(message = "{sample_entitys.sample_entity.validation.description.NotNull.message}")
    @NotBlank(message = "{sample_entitys.sample_entity.validation.description.NotBlank.message}")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
