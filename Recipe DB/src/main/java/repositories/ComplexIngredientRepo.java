package repositories;

import entities.ComplexIngredient;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ComplexIngredientRepo {
    final private EntityManager entityManager;

    public ComplexIngredientRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(ComplexIngredient entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    public ComplexIngredient find(long id) {
        return entityManager.find(ComplexIngredient.class, id);
    }
    public List<ComplexIngredient> findAll() {
        String jpql = "SELECT e from ComplexIngredient e";
        return entityManager.createQuery(jpql, ComplexIngredient.class).getResultList();
    }

    public void update(ComplexIngredient entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
    public void printAll() {
        System.out.println("entities");
        findAll().forEach(System.out::println);
    }
}
