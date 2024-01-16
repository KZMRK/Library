package com.kazmiruk.library.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLoan {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private User reader;
    @ManyToOne
    private Book book;
    private LocalDate borrowAt;

}
