package com.nikhilmohanty.dto;

import com.nikhilmohanty.entity.LibraryBranch;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LibraryBranchDTO {
   private String title;
   private String branchNames;

   public LibraryBranchDTO(String title,String branchNames) {
      this.title = title;
      this.branchNames = branchNames;
   }

}
