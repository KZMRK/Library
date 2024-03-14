package com.kazmiruk.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorResponse {

    private Long id;

    private String firstName;

    private String lastName;
}
