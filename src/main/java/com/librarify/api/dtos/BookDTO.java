package com.librarify.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private Integer pubYear;

    private String isbn;

    private String edition;

    private String language;

    public BookDTO(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
