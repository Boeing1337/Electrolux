package com.kichen.observer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplianceRepository extends CrudRepository<Appliance, Long> {
}
