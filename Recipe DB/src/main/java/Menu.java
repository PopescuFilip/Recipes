import entities.ComplexIngredient;
import entities.Ingredient;
import entities.Recipe;
import entities.SimpleComplexIngredient;
import jakarta.persistence.EntityManager;
import services.RecipeService;

import java.util.*;

public class Menu {
    private final RecipeService service;
    private final Scanner scanner;
    public Menu(EntityManager entityManager) {
        this.service = new RecipeService(entityManager);
        this.scanner = new Scanner(System.in);
    }
    private int getInput() {
        do {
            try {
                int input = this.scanner.nextInt();
                if(input < 0) {
                    System.out.println("Invalid input");
                    continue;
                }
                scanner.nextLine();
                return input;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
                scanner.nextLine();
            }
        } while (true);
    }
    private Recipe pickRecipe() {
        System.out.println("Pick recipe:");
        List<Recipe> entities = service.findAllRecipes();
        if(entities.isEmpty()) {
            System.out.println("no entities to pick from");
            return null;
        }
        int index = 0;
        for(Recipe entity: entities) {
            System.out.println(index + " " + entity.getName());
            index++;
        }
        do {
            int input = getInput();
            if (input < entities.size() && input >= 0)
                return entities.get(input);
            System.out.println("Invalid option");
        } while (true);
    }

    private String pickIngredient() {
        System.out.println("Pick ingredient:");
        Set<String> ingredients = new HashSet<>();
        for(Ingredient ingredient: service.findAllIngredients())
            ingredients.add(ingredient.getName());
        if(ingredients.isEmpty()) {
            System.out.println("no entities to pick from");
            return null;
        }
        for(String ingredient: ingredients) {
            System.out.println(ingredient);
        }
        do {
            String s = scanner.nextLine();
            if (ingredients.contains(s))
                return s;
            System.out.println("Invalid option");
        } while (true);
    }
    private ComplexIngredient getComplexIngredient() {
        System.out.println("Choose complexIngredient name:");
        String name = scanner.nextLine();
        ComplexIngredient complexIngredient = new ComplexIngredient(name);
        System.out.println("Pick number of simpleComplexIngredients");
        int noOfSimpleComplexIngredients = getInput();
        for(int i = 0; i<noOfSimpleComplexIngredients; i++)
            complexIngredient.addSimpleComplexIngredient(getSimpleComplexIngredient());
        System.out.println("Pick number of ingredients");
        int noOfIngredients = getInput();
        for(int i = 0; i<noOfIngredients; i++)
            complexIngredient.addSimpleIngredient(getSimpleIngredient());
        service.save(complexIngredient);
        return complexIngredient;
    }
    private SimpleComplexIngredient getSimpleComplexIngredient() {
        System.out.println("Choose simpleComplexIngredient name:");
        String name = scanner.nextLine();
        SimpleComplexIngredient simpleComplexIngredient = new SimpleComplexIngredient(name);
        System.out.println("Pick number of ingredients");
        int noOfIngredients = getInput();
        for(int i = 0; i<noOfIngredients; i++)
            simpleComplexIngredient.addSimpleIngredient(getSimpleIngredient());
        service.save(simpleComplexIngredient);
        return simpleComplexIngredient;
    }
    private Ingredient getSimpleIngredient() {
        System.out.println("Choose ingredient name:");
        String name = scanner.nextLine();
        Ingredient ingredient = new Ingredient(name);
        service.save(ingredient);
        return ingredient;
    }
    private void  addRecipe() {
        System.out.println("Choose recipe name:");
        String name = scanner.nextLine();
        Recipe recipe = new Recipe(name);
        System.out.println("Pick number of complexIngredients");
        int noOfComplexIngredients = getInput();
        for(int i = 0; i<noOfComplexIngredients; i++)
            recipe.addComplexIngredient(getComplexIngredient());
        System.out.println("Pick number of ingredients");
        int noOfIngredients = getInput();
        for(int i = 0; i<noOfIngredients; i++)
            recipe.addSimpleIngredient(getSimpleIngredient());
        service.save(recipe);
    }

    private Set<String> getIngredientsForRecipe(Recipe recipe) {
        Set<String> ingredients = new HashSet<>();
        for(Ingredient ingredient: recipe.getSimpleIngredients())
            ingredients.add(ingredient.getName());
        for(ComplexIngredient complexIngredient: recipe.getComplexIngredients())
        {
            for(SimpleComplexIngredient simpleComplexIngredient: complexIngredient.getSimpleComplexIngredients())
                for(Ingredient ingredient: simpleComplexIngredient.getSimpleIngredients())
                    ingredients.add(ingredient.getName());
            for(Ingredient ingredient: complexIngredient.getSimpleIngredients())
                ingredients.add(ingredient.getName());
        }

        return ingredients;
    }
    private void showUniqueIngredients() {
        Recipe recipe = pickRecipe();
        for(String ingredient: getIngredientsForRecipe(recipe))
            System.out.println(ingredient);
    }
    private void showAllRecipesForIngredient() {
        String s = pickIngredient();
        boolean found = false;
        for(Recipe recipe: service.findAllRecipes())
            if(getIngredientsForRecipe(recipe).contains(s)){
                found = true;
                System.out.println(recipe.getName());
            }
    }
    private void processMenuOption(int option) {
        switch (option) {
            case 1:
                addRecipe();
                return;
            case 2:
                showUniqueIngredients();
                return;
            case 3:
                showAllRecipesForIngredient();
                return;
            default:
                System.out.println("Invalid option");
        }
    }
    private void showMenu() {
        System.out.println("Menu:");
        System.out.println("0. Exit");
        System.out.println("1. Add recipe");
        System.out.println("2. Show unique ingredients from recipe");
        System.out.println("3. Show all recipes for ingredient");
    }
    public void run() {
        do {
            showMenu();
            int input = getInput();
            if (input == 0) {
                this.scanner.close();
                return;
            }
            processMenuOption(input);
        } while (true);
    }
}
