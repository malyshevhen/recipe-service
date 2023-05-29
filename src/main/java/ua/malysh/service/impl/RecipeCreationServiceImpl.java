package ua.malysh.service.impl;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ua.malysh.domain.Ingredient;
import ua.malysh.domain.Recipe;
import ua.malysh.dto.DishDTO;
import ua.malysh.dto.DishIngredientDTO;
import ua.malysh.service.RecipeCreationService;
import ua.malysh.service.RecipeService;
import ua.malysh.service.exceptions.RecipeAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class RecipeCreationServiceImpl implements RecipeCreationService {
    private final RecipeService service;

    @Override
    public Long postRecipe(DishDTO mealDTO) throws RecipeAlreadyExistsException {
        Recipe recipe = createRecipe(mealDTO);

        return service.save(recipe);
    }

    private Recipe createRecipe(DishDTO mealDTO) {
        Integer portions = mealDTO.getPortions();

        var mealIngredients = mealDTO.getMealIngredients();
        var recipeIngredients = mealIngredients
                .stream()
                .map(i -> extracted(portions, i))
                .collect(Collectors.toSet());

        var recipe = new Recipe();
        recipe.setName(mealDTO.getName());
        recipe.setDescription(mealDTO.getDescription());
        recipe.setPictureUrl(mealDTO.getPictureUrl());
        recipe.addIngredients(recipeIngredients);

        return recipe;
    }

    private Ingredient extracted(Integer portions, DishIngredientDTO ingredientDto) {
        double amount = ingredientDto.getAmount();
        double updatedAmount = amount / portions;

        return new Ingredient(ingredientDto.getProductId(), updatedAmount);
    }
}
