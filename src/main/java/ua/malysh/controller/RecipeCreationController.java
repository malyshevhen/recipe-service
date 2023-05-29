package ua.malysh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ua.malysh.dto.DishDTO;
import ua.malysh.service.RecipeCreationService;

@RestController
@RequestMapping("/api/v1/recipe-management")
@RequiredArgsConstructor
public class RecipeCreationController {
    private RecipeCreationService service;

    @PostMapping
    public ResponseEntity<Long> createRecipe(@RequestBody DishDTO mealDTO) {
        return new ResponseEntity<>(service.postRecipe(mealDTO), HttpStatus.CREATED);
    }
}
