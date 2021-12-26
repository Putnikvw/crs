package com.cloudmore.controller;

import com.cloudmore.dto.ClientDto;
import com.cloudmore.service.ClientService;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClientRegistrationServiceController
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

    @RequestMapping(method = RequestMethod.POST,
            path = "/client",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<ClientDto> createClient(@Validated @RequestBody ClientDto dto) {
        return ResponseEntity.ok(clientService.saveClient(dto));
    }

}
