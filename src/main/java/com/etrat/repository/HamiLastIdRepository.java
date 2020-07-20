package com.etrat.repository;

import com.etrat.domain.Authority;
import com.etrat.domain.HamiLastId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface HamiLastIdRepository extends JpaRepository<HamiLastId, Long> {}
