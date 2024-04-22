package com.kazmiruk.library.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ReaderDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;
}
