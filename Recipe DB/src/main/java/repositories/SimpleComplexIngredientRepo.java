package repositories;
import entities.SimpleComplexIngredient;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SimpleComplexIngredientRepo {
    final private EntityManager entityManager;

    public SimpleComplexIngredientRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(SimpleComplexIngredient entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    public SimpleComplexIngredient find(long id) {
        return entityManager.find(SimpleComplexIngredient.class, id);
    }
    public List<SimpleComplexIngredient> findAll() {
        String jpql = "SELECT e from SimpleComplexIngredient e";
        return entityManager.createQuery(jpql, SimpleComplexIngredient.class).getResultList();
    }

    public void update(SimpleComplexIngredient entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
    public void printAll() {
        System.out.println("entities");
        findAll().forEach(System.out::println);
    }
}
