package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SimpleComplexIngredient {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Ingredient> simpleIngredients = new ArrayList<>();

    public SimpleComplexIngredient() {
    }

    public SimpleComplexIngredient(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addSimpleIngredient(Ingredient ingredient) {
        simpleIngredients.add(ingredient);
    }

    public List<Ingredient> getSimpleIngredients() {
        return simpleIngredients;
    }
}
