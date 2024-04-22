package com.kazmiruk.library.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotEmpty
    private Set<AuthorDto> authors;

    @NotEmpty
    private Set<CategoryDto> categories;

    @NotNull
    private Integer numOfPages;
}
