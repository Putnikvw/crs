package com.cloudmore.controller;

import com.cloudmore.dto.ClientDto;
import com.cloudmore.message.model.KafkaClientMessage;
import com.cloudmore.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientServiceContrioller
 *
 * @author perun
 */
@RestController
@RequestMapping("/api/v1")
public class ClientRegistrationServiceController {

    private final ClientService clientService;

    public ClientRegistrationServiceController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/client-service")
    public ResponseEntity<ClientDto> createClient(@RequestBody KafkaClientMessage message) {
        return ResponseEntity.ok(clientService.saveClient(message));
    }

}
