package com.cloudmore.rest;

import com.cloudmore.builder.ClientBuilder;
import com.cloudmore.controller.ClientRegistrationServiceController;
import com.cloudmore.dto.ClientDto;
import com.cloudmore.service.ClientService;
import com.cloudmore.util.Util;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * ClientServiceControllerTest
 *
 * @author zen
 */

@WebMvcTest(ClientRegistrationServiceController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class ClientServiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value(value = "${api.base-path}")
    private String basePath;

    @Value(value = "${api.client-service-path}")
    private String clientServicePath;

    @MockBean
    private ClientService service;

    @Test
    public void saveClientTest() throws Exception {
        ClientDto clientDto = ClientBuilder.aClient()
                .getFullClient()
                .withEventTime("2012-04-12T18:24:23.511Z")
                .getClientDto();
        when(service.saveClient(Mockito.any())).thenReturn(clientDto);

        String payload = Util.readResource("requests/createClientRequest.json");

        mockMvc.perform(
                MockMvcRequestBuilders.post(basePath + clientServicePath)
                        .content(payload)
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().json(payload));
    }

    @Test
    public void saveClientTest_badRequest() throws Exception {
        ClientDto clientDto = ClientBuilder.aClient()
                .getFullClient()
                .getClientDto();

        when(service.saveClient(Mockito.any())).thenReturn(clientDto);

        String payload = Util.readResource("requests/createClientBadRequest.json");

        mockMvc.perform(
                MockMvcRequestBuilders.post(basePath + clientServicePath)
                        .content(payload)
                        .contentType(APPLICATION_JSON)
        )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("400"))
                .andExpect(jsonPath("$.errors").value("size must be between 2 and 2147483647"));
    }
}
