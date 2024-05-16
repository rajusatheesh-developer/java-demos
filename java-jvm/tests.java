import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DataFormatterController.class)
@SpringJUnitConfig
public class DataFormatterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JsonToXMLConverterService jsonToXMLConverterService;

    @InjectMocks
    private DataFormatterController dataFormatterController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testTransformBatchStatus_Success() throws Exception {
        // Given
        BatchSummaryRequest request = new BatchSummaryRequest();
        String jsonRequest = new ObjectMapper().writeValueAsString(request);
        String expectedResult = "<xml></xml>";

        // Mocking the service method
        when(jsonToXMLConverterService.convertDto(any(BatchSummaryRequest.class))).thenReturn(expectedResult);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.content().string(expectedResult));
    }

    @Test
    public void testTransformBatchStatus_InvalidJson() throws Exception {
        // Given
        String invalidJsonRequest = "{ \"invalid\": \"json\" }";

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content(invalidJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testTransformBatchStatus_ServiceError() throws Exception {
        // Given
        BatchSummaryRequest request = new BatchSummaryRequest();
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        // Mocking the service method to throw an exception
        when(jsonToXMLConverterService.convertDto(any(BatchSummaryRequest.class)))
                .thenThrow(new RuntimeException("Service error"));

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    public void testTransformBatchStatus_NullRequest() throws Exception {
        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testTransformBatchStatus_InvalidMediaType() throws Exception {
        // Given
        BatchSummaryRequest request = new BatchSummaryRequest();
        String jsonRequest = new ObjectMapper().writeValueAsString(request);

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content(jsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnsupportedMediaType());
    }

    @Test
    public void testTransformBatchStatus_EmptyRequestBody() throws Exception {
        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content("")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testTransformBatchStatus_ValidButEmptyRequestBody() throws Exception {
        // Given
        String emptyJsonRequest = "{}";

        // When/Then
        mockMvc.perform(MockMvcRequestBuilders.post("/ddswipr/formatter/format/output")
                .content(emptyJsonRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.content().string(""));
    }

    // Add more test cases for other scenarios as needed
}
