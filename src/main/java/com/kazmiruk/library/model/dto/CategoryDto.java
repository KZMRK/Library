package com.kazmiruk.library.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class CategoryDto {

    private Integer id;

    @NotEmpty
    private String name;
}
