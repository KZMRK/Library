package com.kazmiruk.library.model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ReaderBook {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User reader;

    @OneToOne
    private Book book;

    private LocalDate returnAt;

    public ReaderBook(User reader, Book book, LocalDate returnAt) {
        this.reader = reader;
        this.book = book;
        this.returnAt = returnAt;
    }
}
