package com.kichen.observer.dao;

import com.kichen.observer.service.ApplianceValidator;
import com.kichen.observer.service.EntityUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplianceService {

    /**
     * Just repository of {@link Appliance}
     */
    private final ApplianceRepository repository;

    private final ApplianceValidator validator;

    /**
     * This Class for updating {@link Appliance}
     *
     * @see EntityUpdater
     */
    private final EntityUpdater entityUpdater;

    @Autowired
    public ApplianceService(final ApplianceRepository repository,
                            final ApplianceValidator validator,
                            final EntityUpdater entityUpdater) {
        this.repository = repository;
        this.validator = validator;
        this.entityUpdater = entityUpdater;
    }

    /**
     * Updates or saves {@link Appliance} in the repository
     * Auto updates states and all TimeStamp fields by {@link EntityUpdater}
     *
     * @param newEntity represents correct {@link Appliance} for persisting
     * @see ApplianceValidator
     */
    public void saveStateOf(final Appliance newEntity) {
        final Appliance oldEntity = this.getById(newEntity);

        if (oldEntity == null) {
            entityUpdater.registry(newEntity);
        } else {
            validator.checkTypes(oldEntity, newEntity);
            entityUpdater.updateFromTo(oldEntity, newEntity);
        }
        entityUpdater.setLastUpdateStamp(newEntity);
        this.saveEntity(newEntity);
    }

    /**
     * Saves {@link Appliance} in the repository as it is
     * <p>
     * Only for private using
     *
     * @param appliance represents correct {@link Appliance} for persisting
     */
    private void saveEntity(final Appliance appliance) {
        repository.save(appliance);
    }

    /**
     * Retrieves appropriate entity by its id;
     * <p>
     * You can use {@link #getById(Appliance)} instead of
     *
     * @param id is an unique primary key of {@link Appliance}
     * @return {@link Appliance} if exist or null
     */
    public Appliance getById(final Long id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Retrieves appropriate entity by its id;
     * This method just simplify work with DataBase. You can don't call getId()
     * and just set up {@link Appliance} as parameter
     * <p>
     * You can use {@link #getById(Long)} instead of
     *
     * @param entity is an {@link Appliance}
     * @return {@link Appliance} if exist or null
     */
    public Appliance getById(final Appliance entity) {
        return getById(entity.getId());
    }

}
