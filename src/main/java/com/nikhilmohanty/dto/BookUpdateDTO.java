package com.nikhilmohanty.dto;

import com.nikhilmohanty.entity.LibraryBranch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookUpdateDTO {
    private String Title;
    private List<Long> libraryBranchesId;
}
