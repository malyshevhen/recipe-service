package ua.malysh.service.impl;

import lombok.RequiredArgsConstructor;

import java.util.function.Supplier;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.malysh.domain.Ingredient;
import ua.malysh.domain.Recipe;
import ua.malysh.repository.RecipeRepository;
import ua.malysh.service.RecipeService;
import ua.malysh.service.exceptions.RecipeAlreadyExistsException;
import ua.malysh.service.exceptions.RecipeNotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository repository;

    @Override
    public Long save(Recipe recipe) {
        if (ifPresent(recipe)) {
            throw new RecipeAlreadyExistsException("Recipe with this name already exists!");
        }
        return repository.save(recipe).getId();
    }

    @Override
    public Recipe findById(Long id) {
        return repository.findById(id)
                .orElseThrow(notFundExceptionSupplier());
    }

    @Override
    public Recipe addIngredient(Long id, Ingredient ingredient) {
        var recipe = findById(id);
        recipe.addIngredient(ingredient);

        return recipe;
    }

    @Override
    public Recipe deleteIngredient(Long id, Ingredient ingredient) {
        var recipe = findById(id);
        recipe.deleteIngredient(ingredient);

        return recipe;
    }

    @Override
    public Long deleteById(Long id) {
        var recipe = findById(id);
        repository.delete(recipe);

        return id;
    }

    private boolean ifPresent(Recipe recipe) {
        return repository.findByName(recipe.getName())
                .isPresent();
    }

    private Supplier<? extends RecipeNotFoundException> notFundExceptionSupplier() {
        return () -> new RecipeNotFoundException("Recipe with this id was not found!");
    }
}
