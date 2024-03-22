package com.kazmiruk.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private String name;

    private String description;

    private List<Long> authorIDs;

    private List<Short> categoriesIDs;

    private int numOfPages;
}
