package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@ApplicationScoped
public class FruitRepository {

    private final EntityManager em;

    @Inject
    public FruitRepository(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public Fruit save(final Fruit fruit) {
        return this.em.merge(fruit);
    }

    public Fruit findFruitById(final Integer fruitId) {
        return em.find(Fruit.class, fruitId);
    }
}
