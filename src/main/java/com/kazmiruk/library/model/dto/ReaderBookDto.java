package com.kazmiruk.library.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReaderBookDto {

    private Long id;

    private ReaderDto reader;

    private BookDto book;

    private LocalDate returnAt;

}
