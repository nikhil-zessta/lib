package com.nikhilmohanty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDTO {
    private String title;
    private Long authorId;
    private List<Long> libraryBranchId;
}
