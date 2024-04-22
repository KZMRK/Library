package com.kazmiruk.library.model.dto;

import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AuthorDto {

    private Long id;

    @Size(min = 2, max = 15)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;
}
