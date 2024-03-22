package com.kazmiruk.library.dto;

import com.kazmiruk.library.entities.Author;
import com.kazmiruk.library.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String name;

    private String description;

    private List<AuthorResponse> authors;

    private List<CategoryResponse> categories;

    private int numOfPages;
}
