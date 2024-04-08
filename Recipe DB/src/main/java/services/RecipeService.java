package services;

import entities.ComplexIngredient;
import entities.Ingredient;
import entities.Recipe;
import entities.SimpleComplexIngredient;
import jakarta.persistence.EntityManager;
import repositories.ComplexIngredientRepo;
import repositories.IngredientRepo;
import repositories.RecipeRepo;
import repositories.SimpleComplexIngredientRepo;

import java.util.List;

public class RecipeService {
    private final IngredientRepo ingredientRepo;
    private final ComplexIngredientRepo complexIngredientRepo;
    private final SimpleComplexIngredientRepo simpleComplexIngredientRepo;
    private final RecipeRepo recipeRepo;
    public RecipeService(EntityManager entityManager) {
        ingredientRepo = new IngredientRepo(entityManager);
        this.complexIngredientRepo = new ComplexIngredientRepo(entityManager);
        this.simpleComplexIngredientRepo = new SimpleComplexIngredientRepo(entityManager);
        this.recipeRepo = new RecipeRepo(entityManager);
    }
    public void save(Ingredient entity) {
        ingredientRepo.save(entity);
    }
    public void update(Ingredient entity) {
        ingredientRepo.update(entity);
    }
    public List<Recipe> findAllRecipes() {
        return recipeRepo.findAll();
    }

    public List<Ingredient> findAllIngredients() {
        return ingredientRepo.findAll();
    }
    public void save(SimpleComplexIngredient entity) {
        simpleComplexIngredientRepo.save(entity);
    }
    public void update(SimpleComplexIngredient entity) {
        simpleComplexIngredientRepo.update(entity);
    }
    public List<SimpleComplexIngredient> findAllSimpleComplex() {
        return simpleComplexIngredientRepo.findAll();
    }

    public void save(Recipe entity) {
        recipeRepo.save(entity);
    }

    public void save(ComplexIngredient entity) {
        complexIngredientRepo.save(entity);
    }
}
