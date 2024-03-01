package com.nikhilmohanty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookCreateDTO {
    private String title;
    private Long authorId;
    private List<Long> libraryBranchId;
}
