package org.acme;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class FruitService {

    private final FruitRepository fruitRepository;

    @Inject
    public FruitService(final FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Transactional
    public Fruit saveFruit(final Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    public Fruit findFruitById(final Integer fruitId) {
        return this.fruitRepository.findFruitById(fruitId);
    }
}
