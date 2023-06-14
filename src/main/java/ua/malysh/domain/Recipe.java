package ua.malysh.domain;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "recipes", indexes = {
        @Index(name = "idx_recipes_recipe_id", columnList = "recipe_id") }, uniqueConstraints = {
                @UniqueConstraint(name = "uc_recipes_recipe_name", columnNames = { "recipe_name" }) })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "recipe_id",
                    nullable = false)
    private Long id;

    @Column(name = "recipe_name",
                    nullable = false)
    private String name;

    @ElementCollection
    @CollectionTable(name = "recipe_ingredients",
                     joinColumns = @JoinColumn(name = "recipe_id"))
    @Column(name = "ingredient")
    @Setter(AccessLevel.PRIVATE)
    private Set<Ingredient> ingredients = new HashSet<>();

    @Column(name = "recipe_description")
    private String description;

    @Column(name = "recipe_picture_url")
    private String pictureUrl;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public void addIngredients(Set<Ingredient> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public void deleteIngredient(Ingredient ingredient) {
        this.ingredients.removeAll(Set.of(ingredient));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o))
            return false;
        Recipe recipe = (Recipe) o;
        return getId() != null && Objects.equals(getId(), recipe.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
