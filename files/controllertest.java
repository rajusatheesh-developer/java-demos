package com.tsys.enterprise.ddswipformatterapi.controller;

import com.tsys.enterprise.ddswipformatterapi.domain.BatchSummaryRequest;
import com.tsys.enterprise.ddswipformatterapi.service.JsonToXMLConverterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataFormatterController.class)
public class DataFormatterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JsonToXMLConverterService jsonToXMLConverterService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testTransformBatchStatus() throws Exception {
        String jsonInput = "{ \"key\": \"value\" }";
        String xmlOutput = "<root><key>value</key></root>";

        when(jsonToXMLConverterService.convertTo(any(BatchSummaryRequest.class))).thenReturn(xmlOutput);

        mockMvc.perform(post("/ddswip/formatter/format/output")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
                .content(jsonInput))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_XML))
                .andExpect(content().xml(xmlOutput));
    }

    @Test
    public void testTransformBatchStatusBadRequest() throws Exception {
        String invalidJsonInput = "invalid json";

        mockMvc.perform(post("/ddswip/formatter/format/output")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML)
                .content(invalidJsonInput))
                .andExpect(status().isBadRequest());
    }
}
