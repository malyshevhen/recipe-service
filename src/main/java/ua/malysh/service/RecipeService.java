package ua.malysh.service;

import ua.malysh.domain.Ingredient;
import ua.malysh.domain.Recipe;

public interface RecipeService {
    Long save(Recipe recipe);

    Recipe findById(Long id);

    Long deleteById(Long id);

    Recipe addIngredient(Long id, Ingredient ingredient);

    Recipe deleteIngredient(Long id, Ingredient ingredient);
}
