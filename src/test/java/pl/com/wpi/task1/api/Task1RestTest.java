package pl.com.wpi.task1.api;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import pl.com.wpi.task1.dao.TemperatureRepository;
import pl.com.wpi.task1.model.Temperature;


@SpringBootTest
@AutoConfigureMockMvc
class Task1RestTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TemperatureRepository repo;

    @Test
    void httpGetAll_returnsStatus200() throws Exception {
        repo.save(new Temperature(0, 10));

        mockMvc.perform(MockMvcRequestBuilders.get("/temperature/values/")).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void httpPost_returnsStatus200() throws Exception {
        repo.save(new Temperature(0, 10));

        mockMvc.perform(MockMvcRequestBuilders.post("/temperature/value/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"localization\": 1,\n" + "\"value\": 4}"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }


}