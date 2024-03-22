package com.kazmiruk.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(min = 4, max = 15)
    private String firstName;
    @Size(min = 2, max = 20)
    private String lastName;
    @JsonFormat(pattern = "dd-MM-yyyy")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Date should be in the format dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @Email
    private String email;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,15}$",
            message = "Password must contain at least one uppercase letter, " +
                    "one lowercase letter, one digit, and be between 8 and 15 characters long"
    )
    private String password;
}
