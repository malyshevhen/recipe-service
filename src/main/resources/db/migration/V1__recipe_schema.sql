CREATE TABLE recipes
(
    recipe_id           BIGSERIAL,
    recipe_name         TEXT NOT NULL,
    recipe_description  TEXT,
    recipe_picture_url  TEXT,
    created_at          TIMESTAMP,
    modified_at         TIMESTAMP,
    CONSTRAINT pk_recipes_recipe_id PRIMARY KEY (recipe_id),
    CONSTRAINT uc_recipes_recipe_name UNIQUE (recipe_name)
);
CREATE TABLE recipe_ingredients
(
    recipe_id           BIGINT,
    product_id          BIGINT NOT NULL,
    product_name        TEXT NOT NULL,
    ingredient_amount   DOUBLE PRECISION NOT NULL,
    CONSTRAINT pk_recipe_ingredients_ingredient_id PRIMARY KEY (recipe_id),
    CONSTRAINT fk_recipe_ingredients_ingredient_id FOREIGN KEY (recipe_id) REFERENCES recipes(recipe_id)
);