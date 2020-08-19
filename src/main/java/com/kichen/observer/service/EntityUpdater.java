package com.kichen.observer.service;

import com.kichen.observer.dao.Appliance;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This Class for updating {@link Appliance}
 * It can create TimeStamps
 * or bring fields from an old entity to a new
 */
@Component
public class EntityUpdater {

    /**
     * Just set a {@link Timestamp} to a {@link Stampable}'s field
     * <p>
     * Just like a time sign that signals about last update time of entity
     *
     * @param stampable class implementing {@link Stampable}' interface
     */
    public void setLastUpdateStamp(final Stampable stampable) {
        stampable.setLastUpdate(this.getTimeStampNow());
    }

    /**
     * This method brings values from an old entity to a new.
     * <p>
     * If @value state of an new entity is updated then @value stateChanged would be updated
     * brought fields are:
     * registryDate {@link Timestamp}
     * stateChanged {@link Timestamp} is the field is not changed
     *
     * @param old  is an {@link Appliance} old entity
     * @param aNew is an {@link Appliance} new entity
     */
    public void updateFromTo(final Appliance old, final Appliance aNew) {
        aNew.setRegistryDate(old.getRegistryDate());
        if (aNew.getState() != old.getState()) {
            updateStateChangedStamp(aNew);
        } else {
            aNew.setStateChanged(old.getStateChanged());
        }
    }

    /**
     * This method fills required empty fields of a new {@link Appliance} entity
     *
     * @param newEntity {@link Appliance} not existing in an appropriate repository
     */
    public void registry(final Appliance newEntity) {
        setRegistryStamp(newEntity);
        updateStateChangedStamp(newEntity);
    }

    /**
     * This method set {@link Timestamp} into stateChanged filed of the {@link Appliance}
     * TimeStamp will be created from LocalDateTime.now()
     *
     * @param appliance an entity to set up {@link Timestamp}
     */
    public void updateStateChangedStamp(final Appliance appliance) {
        appliance.setStateChanged(this.getTimeStampNow());
    }

    /**
     * Create TimeStamp from LocalDateTime.now()
     * This method is used by many methods is the Class
     * <p>
     * You can make this method public and feel free in using it
     *
     * @return {@link Timestamp} from LocalDateTime.now()
     */
    private Timestamp getTimeStampNow() {
        return Timestamp.valueOf(LocalDateTime.now());
    }

    /**
     * This method set {@link Timestamp} into registryDate filed of the {@link Appliance}
     * TimeStamp will be created from LocalDateTime.now()
     *
     * @param appliance an entity to set up {@link Timestamp}
     */
    private void setRegistryStamp(final Appliance appliance) {
        appliance.setRegistryDate(this.getTimeStampNow());
    }
}
