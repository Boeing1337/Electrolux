package com.kichen.observer.controllers;

import com.kichen.observer.dao.Appliance;
import com.kichen.observer.dao.ApplianceRepository;
import com.kichen.observer.dao.ApplianceService;
import com.kichen.observer.service.ApplianceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("check/in")
public class ApplianceController {
    /**
     * DAO service for {@link ApplianceRepository}
     */
    private final ApplianceService service;
    /**
     * Validator checks main fields of {@link Appliance} entity for correctness
     */
    private final ApplianceValidator validator;

    @Autowired
    public ApplianceController(final ApplianceService service,
                               final ApplianceValidator entityValidator) {
        this.service = service;
        this.validator = entityValidator;
    }

    /**
     * This method saves STATE of an appliance by post request
     *
     * @param appliance is a raw {@link Appliance} from request JSON
     * @throws IllegalStateException if a JSON has incorrect or less parameters
     */
    @PostMapping(consumes = "application/json")
    public void saveApplianceState(@RequestBody final Appliance appliance) {
        if (validator.isValid(appliance)) {
            service.saveStateOf(appliance);
        } else {
            throw new IllegalStateException("Either appliance type or state aren't appropriate");
        }
    }

    /**
     * This method gets {@link Appliance} from DataBase through DAO service
     *
     * @param id is a primary id key of an {@link Appliance}
     * @return {@link Appliance} if found
     * @throws IllegalArgumentException if entity is not exist
     * @see ApplianceService
     */
    @GetMapping(params = "id", produces = "application/json")
    public Appliance saveApplianceState(@RequestParam final Long id) {
        final Appliance appliance = service.getById(id);
        if (appliance == null) {
            throw new IllegalArgumentException("Entity with this id is not present");
        } else {
            return appliance;
        }
    }


}