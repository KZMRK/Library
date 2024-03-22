package com.kazmiruk.library.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorRequest {

    @Size(min = 2, max = 15)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;
}
