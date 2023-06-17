package unit.common.controller;

import com.depromeet.common.controller.HealthController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = HealthController.class)
@WebMvcTest(controllers = {HealthController.class}, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@Import(HealthController.class)
@DisplayName("[API][Controller] 상태 체크용 API 테스트")
public class HealthControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("[GET] 상태 체크용 API 테스트")
    void testHealthCheckTest() throws Exception {
        mvc.perform(get("/health")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
