package repositories;

import entities.Recipe;
import jakarta.persistence.EntityManager;

import java.util.List;

public class RecipeRepo {
    final private EntityManager entityManager;

    public RecipeRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Recipe entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    public Recipe find(long id) {
        return entityManager.find(Recipe.class, id);
    }
    public List<Recipe> findAll() {
        String jpql = "SELECT e from Recipe e";
        return entityManager.createQuery(jpql, Recipe.class).getResultList();
    }

    public void update(Recipe entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
    public void printAll() {
        System.out.println("entities");
        findAll().forEach(System.out::println);
    }
}
