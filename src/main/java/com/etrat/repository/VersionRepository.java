package com.etrat.repository;

import com.etrat.domain.Authority;
import com.etrat.domain.VersionEntity;
import com.etrat.service.dto.OperationSystem;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface VersionRepository extends JpaRepository<VersionEntity, Long> {
    List<VersionEntity> findByAppVersionAndOperationSystem(String appVersion, OperationSystem operationSystem);
}
