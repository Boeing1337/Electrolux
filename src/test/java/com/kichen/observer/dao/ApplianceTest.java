package com.kichen.observer.dao;

import com.kichen.observer.appliance.ApplianceState;
import com.kichen.observer.appliance.ApplianceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.kichen.observer.appliance.ApplianceState.SPIN;
import static com.kichen.observer.appliance.ApplianceType.WASHMACHINE;
import static org.junit.jupiter.api.Assertions.*;

class ApplianceTest {
    private static Appliance appliance;
    private final Timestamp timeStamp = Timestamp.valueOf(LocalDateTime.now());

    @BeforeEach
    public void resetAppliance() {
        appliance = new Appliance();
    }

    @Test
    void hasDefaultParameters0() {
        assertFalse(appliance.isDataPresents());
    }

    @Test
    void hasDefaultParameters2() {
        appliance.setType(WASHMACHINE);
        assertFalse(appliance.isDataPresents());
    }

    @Test
    void hasDefaultParameters3() {
        appliance.setType(WASHMACHINE);
        appliance.setState(SPIN);
        assertFalse(appliance.isDataPresents());
    }

    @Test
    void hasDefaultParameters4() {
        appliance.setType(WASHMACHINE);
        appliance.setState(SPIN);
        appliance.setId(16L);
        assertTrue(appliance.isDataPresents());
    }

    @Test
    void getId0() {
        final Long testId = 16L;
        appliance.setId(testId);
        assertEquals(testId, appliance.getId());
    }

    @Test
    void getType() {
        final ApplianceType type = WASHMACHINE;
        appliance.setType(type);
        assertEquals(type, appliance.getType());
    }

    @Test
    void getState() {
        final ApplianceState state = SPIN;
        appliance.setState(state);
        assertEquals(state, appliance.getState());
    }

    @Test
    void getRegistryDate() {
        appliance.setRegistryDate(timeStamp);
        assertEquals(timeStamp, appliance.getRegistryDate());
    }

    @Test
    void getStateChanged() {
        appliance.setStateChanged(timeStamp);
        assertEquals(timeStamp, appliance.getStateChanged());
    }

    @Test
    void getLastUpdate() {
        appliance.setLastUpdate(timeStamp);
        assertEquals(timeStamp, appliance.getLastUpdate());
    }

    @Test
    void setId() {
        final Long testId = 16L;
        appliance.setId(testId);
        assertEquals(testId, appliance.getId());
    }

    @Test
    void setType() {
        final ApplianceType type = WASHMACHINE;
        appliance.setType(type);
        assertEquals(type, appliance.getType());
    }

    @Test
    void setState() {
        final ApplianceState state = SPIN;
        appliance.setState(state);
        assertEquals(state, appliance.getState());
    }

    @Test
    void setRegistryDate() {
        appliance.setRegistryDate(timeStamp);
        assertEquals(timeStamp, appliance.getRegistryDate());
    }

    @Test
    void setStateChanged() {
        appliance.setStateChanged(timeStamp);
        assertEquals(timeStamp, appliance.getStateChanged());
    }

    @Test
    void setLastUpdate() {
        appliance.setLastUpdate(timeStamp);
        assertEquals(timeStamp, appliance.getLastUpdate());
    }
}