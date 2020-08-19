package com.kichen.observer.service;

import com.kichen.observer.dao.Appliance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static com.kichen.observer.appliance.ApplianceState.HOLD;
import static com.kichen.observer.appliance.ApplianceState.WASH;
import static org.junit.jupiter.api.Assertions.*;

class EntityUpdaterTest {
    private final EntityUpdater entityUpdater = new EntityUpdater();
    private Appliance appliance;

    @BeforeEach
    void setUp() {
        appliance = new Appliance();
    }

    @Test
    void setLastUpdateStamp() {
        entityUpdater.setLastUpdateStamp(appliance);
        assertNotNull(appliance.getLastUpdate());

    }

    @Test
    void updateFromTo0() {
        final Timestamp registryStamp = Timestamp.valueOf(LocalDateTime.now());
        final Timestamp stateChangedStamp = Timestamp.valueOf(LocalDateTime.now());
        final Appliance oldAppliance = new Appliance();
        oldAppliance.setRegistryDate(registryStamp);
        oldAppliance.setStateChanged(stateChangedStamp);
        oldAppliance.setState(WASH);
        final Timestamp updatedStateStamp;

        entityUpdater.updateFromTo(oldAppliance, appliance);//new state null
        assertEquals(registryStamp, appliance.getRegistryDate()); //registry date shouldn't be changed
        assertNotEquals(stateChangedStamp, appliance.getStateChanged()); //check that new stateStamp is created

        updatedStateStamp = appliance.getStateChanged(); //save new stateStamp
        oldAppliance.setStateChanged(updatedStateStamp); // updating old data;
//**********************************************************

        appliance.setState(WASH); //use old state
        entityUpdater.updateFromTo(oldAppliance, appliance);

        assertEquals(appliance.getRegistryDate(), registryStamp); //registry date shouldn't be changed
        assertEquals(appliance.getStateChanged(), updatedStateStamp); //check that stateStamp is old
//**********************************************************

        appliance.setState(HOLD); //use new state
        entityUpdater.updateFromTo(oldAppliance, appliance);

        assertEquals(appliance.getRegistryDate(), registryStamp); //registry date shouldn't be changed
        assertNotEquals(appliance.getStateChanged(), updatedStateStamp); //check that new stateStamp is created
    }


    @Test
    void registry() {
        entityUpdater.registry(appliance);
        assertNotNull(appliance.getRegistryDate());
        assertNotNull(appliance.getStateChanged());
    }

    @Test
    void UpdateStateStamp() {
        entityUpdater.updateStateChangedStamp(appliance);
        assertNotNull(appliance.getStateChanged());
    }
}

