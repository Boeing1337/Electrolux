package com.kichen.observer.service;

import com.kichen.observer.dao.Appliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.kichen.observer.appliance.ApplianceState.WASH;
import static com.kichen.observer.appliance.ApplianceType.OVEN;
import static com.kichen.observer.appliance.ApplianceType.WASHMACHINE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ApplianceValidatorTest {

    private final ApplianceValidator applianceValidator = new ApplianceValidator();
    private Appliance appliance;

    @BeforeEach
    public void before() {
        appliance = new Appliance();
    }

    @Test
    void isValid1() {
        assertFalse(applianceValidator.isValid(appliance));
    }

    @Test
    void isValid2() {
        appliance.setId(99L);
        assertFalse(applianceValidator.isValid(appliance));
    }

    @Test
    void isValid3() {
        appliance.setId(99L);
        appliance.setState(WASH);
        assertFalse(applianceValidator.isValid(appliance));
    }

    @Test
    void isValid4() {
        appliance.setId(99L);
        appliance.setState(WASH);
        assertFalse(applianceValidator.isValid(appliance));
    }

    @Test
    void isValid5() {
        appliance.setId(99L);
        appliance.setState(WASH);
        appliance.setType(OVEN);
        assertFalse(applianceValidator.isValid(appliance));
    }

    @Test
    void isValid6() {
        appliance.setId(99L);
        appliance.setState(WASH);
        appliance.setType(WASHMACHINE);
        assertTrue(applianceValidator.isValid(appliance));
    }
}