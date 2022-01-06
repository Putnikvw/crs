package com.cloudmore.controller;

import com.cloudmore.dto.ClientDto;
import com.cloudmore.exception.CustomException;
import com.cloudmore.service.ClientService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @Operation(summary = "Gets all users", tags = "user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Create client",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ClientDto.class)))
                    }),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid parameters",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CustomException.class)
                    )
            )
    })
    @RequestMapping(method = RequestMethod.POST,
            path = "/client",
            produces = "application/json",
            consumes = "application/json")
    public ResponseEntity<ClientDto> createClient(@Validated @RequestBody ClientDto dto) {
        return new ResponseEntity<>(clientService.saveClient(dto), HttpStatus.CREATED);
    }

}
