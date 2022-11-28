package com.airlines.ap.api.selfservice.domain.spi;

import com.airlines.ap.api.selfservice.domain.audit.Audit;

public interface IAuditRepository {
	public Audit createAudit(Audit audit);

}
