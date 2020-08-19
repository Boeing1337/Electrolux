package com.kichen.observer.service;

import com.kichen.observer.dao.Appliance;

import java.sql.Timestamp;

/**
 * This interface allows an implementing class keep {@link Timestamp} of LastUpdate time
 *
 * @value lastUpdate must exist in implementing class
 * @see EntityUpdater class which uses this interface as parameter of methods
 * @see Appliance class which implementing this interface
 */
public interface Stampable {
    Timestamp getLastUpdate();
    void setLastUpdate(Timestamp lastUpdate);
}

