package ua.malysh.dto;

import java.util.Set;

import lombok.Data;

@Data
public class DishDTO {
    private String name;
    private Integer portions;
    private String description;
    private String pictureUrl;
    private Set<DishIngredientDTO> mealIngredients;
}
