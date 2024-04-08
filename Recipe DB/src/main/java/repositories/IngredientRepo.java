package repositories;

import entities.Ingredient;
import jakarta.persistence.EntityManager;

import java.util.List;

public class IngredientRepo {
    final private EntityManager entityManager;

    public IngredientRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(Ingredient entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
    public void remove(Ingredient entity) {
        Ingredient foundEntity = find(entity.getId());
        entityManager.getTransaction().begin();
        entityManager.remove(foundEntity);
        entityManager.getTransaction().commit();
    }
    public Ingredient find(long id) {
        return entityManager.find(Ingredient.class, id);
    }
    public List<Ingredient> findAll() {
        String jpql = "SELECT e from Ingredient e";
        return entityManager.createQuery(jpql, Ingredient.class).getResultList();
    }

    public void update(Ingredient entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }
    public void printAll() {
        System.out.println("entities");
        findAll().forEach(System.out::println);
    }
    public void removeAll() {
        List<Ingredient> list = findAll();
        for (Ingredient entity: list) {
            remove(entity);
        }
    }
}
