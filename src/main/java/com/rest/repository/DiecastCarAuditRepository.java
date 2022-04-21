package com.rest.repository;

import com.rest.entities.DiecastCarAudit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author u2cc
 */
@Repository
public interface DiecastCarAuditRepository extends CrudRepository<DiecastCarAudit, Long> {
}
