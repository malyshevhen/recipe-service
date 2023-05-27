package ua.malysh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Ingredient {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "ingredient_amount", nullable = false)
    private Double amount;
}
