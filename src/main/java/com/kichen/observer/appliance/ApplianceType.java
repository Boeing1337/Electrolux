package com.kichen.observer.appliance;

import java.util.Set;

import static com.kichen.observer.appliance.ApplianceState.*;

/**
 * This enum represents all possible types of appliances
 *
 * @see ApplianceState
 */
public enum ApplianceType {
    WASHMACHINE(Set.of(
            DRAIN, SPIN, DRYING, SHUTDOWN, WASH, QUICK_WASH, WASH_SHOES, RINSE,
            OUTDOOR_CARE, SOAK, HOLD
    )),
    OVEN(Set.of(
            GRILL, SHUTDOWN, WARM_UP, HOLD
    ));

    private final Set<ApplianceState> applianceStateSet;

    ApplianceType(final Set<ApplianceState> applianceStates) {
        this.applianceStateSet = applianceStates;
    }

    public Set<ApplianceState> getApplianceStateSet() {
        return applianceStateSet;
    }
}
