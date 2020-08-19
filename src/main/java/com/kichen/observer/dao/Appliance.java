package com.kichen.observer.dao;

import com.kichen.observer.appliance.ApplianceState;
import com.kichen.observer.appliance.ApplianceType;
import com.kichen.observer.service.Stampable;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Simple entity for {@link ApplianceRepository}
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "Appliances")
public class Appliance implements Stampable {
    @Id
    @Column(name = "id")
    private Long id;

    /**
     * @value machineType represents {@link ApplianceType} of an {@link Appliance}.
     */
    @Column(name = "type")
    private ApplianceType type;

    /**
     * @value state represents {@link ApplianceState} of an {@link Appliance}.
     */
    @Column(name = "state")
    private ApplianceState state;

    /**
     * @value registryDate represents {@link Timestamp} when an {@link Appliance}
     * was registered in the system.
     */
    @Column(name = "registry_date")
    private Timestamp registryDate;

    /**
     * @value stateChange represents {@link Timestamp} when state of an {@link Appliance}
     * was updated.
     */
    @Column(name = "state_changed")
    private Timestamp stateChanged;

    /**
     * @value lastUpdate represents {@link Timestamp} when information
     * of an appropriate {@link Appliance} was updated.
     */
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    /**
     * method for check if default parameters are not null.
     * default parameters are: id, state, type
     * <p>
     * UPDATE: may be it's a bad idea but an experiment
     *
     * @return boolean for appropriate check
     */
    public boolean isDataPresents() {
        return (id != null && state != null && type != null);
    }

}
