package com.kichen.observer.dao;

import com.kichen.observer.appliance.ApplianceState;
import com.kichen.observer.appliance.ApplianceType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;

import static com.kichen.observer.appliance.ApplianceState.WASH;
import static com.kichen.observer.appliance.ApplianceType.OVEN;
import static com.kichen.observer.appliance.ApplianceType.WASHMACHINE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ApplianceServiceTest {
    final Long id = 16L;
    final ApplianceState state = WASH;
    final ApplianceType type = WASHMACHINE;

    @Autowired
    private ApplianceRepository applianceRepository;

    @Autowired
    private ApplianceService service;

    @Test
    void delete() {
        applianceRepository.deleteById(id);
    }

    @Test
    void saveStateOf0() {
        final Appliance appliance = new Appliance();
        appliance.setId(id);
        appliance.setState(state);
        appliance.setType(type);
        service.saveStateOf(appliance);
        //check for no exception with legal values
    }

    @Test
    void saveStateOf1() {
        final Appliance appliance = new Appliance();
        appliance.setId(id);
        appliance.setState(state);
        appliance.setType(OVEN); //changing type of the entity
        assertThrows(IllegalStateException.class, () ->
                service.saveStateOf(appliance));
    }

    @Test
    void getById0() {
        Timestamp registryDate;
        Timestamp lastUpdate;
        Timestamp stateChanged;
        
        Appliance appliance = service.getById(id);
        assertEquals(appliance.getState(), state);
        assertEquals(appliance.getType(), type);
        assertEquals(appliance.getId(), id);
        assertNotNull(appliance.getRegistryDate());
        assertNotNull(appliance.getStateChanged());
        assertNotNull(appliance.getLastUpdate());

        registryDate = appliance.getRegistryDate();
        stateChanged = appliance.getStateChanged();
        lastUpdate = appliance.getLastUpdate();
//*********************************
        Appliance pseudoMock = new Appliance();
        pseudoMock.setId(id);
        appliance = service.getById(pseudoMock);
        assertEquals(appliance.getState(), state);
        assertEquals(appliance.getType(), type);
        assertEquals(appliance.getId(), id);

        assertEquals(registryDate, appliance.getRegistryDate());
        assertEquals(stateChanged, appliance.getStateChanged());
        assertEquals(lastUpdate, appliance.getLastUpdate());
//*********************************
        appliance = new Appliance();
        appliance.setId(id);
        appliance.setState(state);
        appliance.setType(type);
        service.saveStateOf(appliance);
        Appliance applianceUpdated = service.getById(id);

        assertNotEquals(lastUpdate, applianceUpdated.getLastUpdate());
        assertEquals(stateChanged, applianceUpdated.getStateChanged());
        assertEquals(registryDate, applianceUpdated.getRegistryDate());

    }

}