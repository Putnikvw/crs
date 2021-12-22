package com.cloudmore.repository;

import com.cloudmore.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ClientServiceRepository
 *
 * @author perun
 */
public interface ClientServiceRepository extends JpaRepository<Client, Long> {
}
