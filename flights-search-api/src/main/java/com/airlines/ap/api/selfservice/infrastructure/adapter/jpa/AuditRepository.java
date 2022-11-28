package com.airlines.ap.api.selfservice.infrastructure.adapter.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.airlines.ap.api.selfservice.domain.audit.Audit;
import com.airlines.ap.api.selfservice.domain.spi.IAuditRepository;

public interface AuditRepository extends CrudRepository<Audit, String>,
JpaSpecificationExecutor<Audit>, IAuditRepository{
	
	public default Audit createAudit(Audit audit) {
		return this.save(audit);
	};

}
