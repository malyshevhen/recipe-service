package ua.malysh.service;

import ua.malysh.dto.DishDTO;

public interface RecipeCreationService {

    Long postRecipe(DishDTO mealDTO);

}
