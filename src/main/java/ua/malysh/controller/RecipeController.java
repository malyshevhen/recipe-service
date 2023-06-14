package ua.malysh.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.malysh.domain.Ingredient;
import ua.malysh.domain.Recipe;
import ua.malysh.service.RecipeService;

@RestController
@RequestMapping("/api/v1/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody Recipe recipe) {
        return new ResponseEntity<>(recipeService.save(recipe), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> findById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}/addIngredient")
    public ResponseEntity<Recipe> addIngredient(@PathVariable Long id,
                                                @RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(recipeService.addIngredient(id, ingredient), HttpStatus.OK);
    }

    @PutMapping("/{id}/deleteIngredient")
    public ResponseEntity<Recipe> deleteIngredient(@PathVariable Long id,
                                                   @RequestBody Ingredient ingredient) {
        return new ResponseEntity<>(recipeService.deleteIngredient(id, ingredient), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) {
        return new ResponseEntity<>(recipeService.deleteById(id), HttpStatus.OK);
    }
}
