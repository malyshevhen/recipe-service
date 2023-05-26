package ua.malysh.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ua.malysh.domain.Ingredient;
import ua.malysh.domain.Product;
import ua.malysh.domain.Recipe;
import ua.malysh.service.RecipeService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = RecipeController.class)
class RecipeControllerTest {
    private static final String URL = "/api/v1/recipes";

    @MockBean
    private RecipeService service;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldReturnStatusCreated() throws Exception {
        long createdId = 1L;

        var product = new Product(1L, "Test product");

        var ingredient = new Ingredient();
        ingredient.setAmount(2.2D);
        ingredient.setProduct(product);

        var recipe = new Recipe();
        recipe.setName("Test recipe");
        recipe.addIngredient(ingredient);

        when(service.save(recipe)).thenReturn(createdId);

        mockMvc.perform(post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(recipe)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnStatusOkAndReturnRetrievedRecipe() throws Exception {
        long id = 1L;
        var product = new Product(1L, "Test product");

        var ingredient = new Ingredient();
        ingredient.setAmount(2.2D);
        ingredient.setProduct(product);

        var recipe = new Recipe();
        recipe.setId(id);
        recipe.setName("Test recipe");
        recipe.addIngredient(ingredient);

        when(service.findById(id)).thenReturn(recipe);

        mockMvc.perform(get(URL + "/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(id))
                .andExpect(jsonPath("name").value(recipe.getName()))
                .andDo(print());
    }

    @Test
    void whenDeleteExistingRecipeShouldReturnStatusOk() throws Exception {
        long id = 1L;

        when(service.deleteById(id)).thenReturn(id);

        mockMvc.perform(delete(URL + "/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }
}