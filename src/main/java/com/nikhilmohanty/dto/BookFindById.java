package com.nikhilmohanty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookFindById {
    private String title;
    private String author;
    private List<String> libraryBranches;
}
