package com.nikhilmohanty.dto;

import com.nikhilmohanty.entity.LibraryBranch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class BookUpdateDTO {
    private String Title;
    private List<Long> libraryBranchesId;
}
