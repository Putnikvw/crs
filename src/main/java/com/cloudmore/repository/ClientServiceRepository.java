package com.cloudmore.repository;

import com.cloudmore.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ClientServiceRepository
 *
 * @author perun
 */
@Repository
public interface ClientServiceRepository extends JpaRepository<Client, Long> {
}
