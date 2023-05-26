CREATE TABLE recipes
(
    recipe_id BIGSERIAL,
    recipe_name TEXT NOT NULL,
    CONSTRAINT pk_recipes_recipe_id PRIMARY KEY (recipe_id),
    CONSTRAINT uc_recipes_recipe_name UNIQUE (recipe_name)
);
CREATE TABLE ingredients
(
    ingredient_id BIGSERIAL,
    product_id BIGINT NOT NULL,
    product_name TEXT NOT NULL,
    ingredient_amount REAL NOT NULL,
    CONSTRAINT pk_ingredients_ingredient_id PRIMARY KEY (ingredient_id)
);