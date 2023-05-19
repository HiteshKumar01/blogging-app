package com.bloggingapp.dtopayloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

    private Integer categoryId;
    @NotBlank
    @Size(max=50,message = "Title size must be under 30")
    private String categoryTitle;

    @NotBlank
    @Size(min=10, max=100,message="Description must be between 10 to 100 characters")
    private String categoryDescription;
}
