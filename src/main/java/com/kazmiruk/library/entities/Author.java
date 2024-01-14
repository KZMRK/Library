package com.kazmiruk.library.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {

    @Id
    @GeneratedValue
    private int id;
    private String firstName;
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "authors")
    @JsonBackReference
    private List<Book> books;
}
