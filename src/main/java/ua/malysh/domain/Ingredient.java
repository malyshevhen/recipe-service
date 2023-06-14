package ua.malysh.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "ingredient_amount", nullable = false)
    private Double amount;
}
