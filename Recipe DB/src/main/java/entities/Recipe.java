package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    @OneToMany
    private List<Ingredient> simpleIngredients = new ArrayList<>();
    @OneToMany
    private List<SimpleComplexIngredient> simpleComplexIngredients = new ArrayList<>();
    @OneToMany
    private List<ComplexIngredient> complexIngredients = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(String name) {
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
    public void addComplexIngredient(ComplexIngredient ingredient) {
        complexIngredients.add(ingredient);
    }

    public List<ComplexIngredient> getComplexIngredients() {
        return complexIngredients;
    }

    public void addSimpleIngredient(Ingredient ingredient) {
        simpleIngredients.add(ingredient);
    }

    public void addSimpleComplexIngredient(SimpleComplexIngredient ingredient) {
        simpleComplexIngredients.add(ingredient);
    }

    public List<Ingredient> getSimpleIngredients() {
        return simpleIngredients;
    }

    public List<SimpleComplexIngredient> getSimpleComplexIngredients() {
        return simpleComplexIngredients;
    }
}
