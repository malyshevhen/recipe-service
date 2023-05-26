package ua.malysh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class Ingredient {

    @Embedded
    private Product product;

    @Column(name = "ingredient_amount", nullable = false)
    private Double amount;
}
