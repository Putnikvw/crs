package com.cloudmore.repository;

import com.cloudmore.builder.ClientBuilder;
import com.cloudmore.domain.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * ClientServiceRepository
 *
 * @author zen
 */

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
public class ClientServiceRepositoryTest {

    @Autowired
    private ClientServiceRepository repository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void saveClientTest() {
        Client savedClient = saveAndFlushClient();
        Client fetchedClient = repository.findAll().get(0);

        assertNotNull(fetchedClient);
        assertThat(fetchedClient).usingRecursiveComparison().isEqualTo(savedClient);
    }

    private Client saveAndFlushClient() {
        Client client = ClientBuilder.aClient().getFullClient().getClientModel();
        repository.save(client);

        testEntityManager.flush();
        testEntityManager.clear();

        return client;
    }
}
