package com.kichen.observer.service;

import com.kichen.observer.appliance.ApplianceState;
import com.kichen.observer.appliance.ApplianceType;
import com.kichen.observer.dao.Appliance;
import org.springframework.stereotype.Component;

/**
 * This class is used for check if {@link Appliance} has correct fields
 */
@Component
public class ApplianceValidator {

    /**
     * This method checks fields of {@link Appliance} type for correctness
     *
     * @param entity represent a {@link Appliance}
     * @return true true if there are all fields are correct or false if not
     * @see ApplianceState
     * @see ApplianceType
     */
    public boolean isValid(final Appliance entity) {
        if (!entity.isDataPresents()) {
            return false;
        }
        final ApplianceState state = entity.getState();
        return entity.getType()
                .getApplianceStateSet()
                .stream()
                .anyMatch(e -> e.equals(state));
    }

    /**
     * This method check types of two difference {@link Appliance}s
     *
     * @param old  is first {@link Appliance}
     * @param aNew is second {@link Appliance}
     * @throws IllegalStateException if states are not equals
     */
    public void checkTypes(final Appliance old, final Appliance aNew) {
        if (old.getType() != aNew.getType()) {
            throw new IllegalStateException("New entity has another type");
        }
    }
}
