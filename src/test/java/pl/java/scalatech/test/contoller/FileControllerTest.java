package pl.java.scalatech.test.contoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import lombok.extern.slf4j.Slf4j;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.path.json.JsonPath;

import pl.java.scalatech.config.AppConfig;
import static org.springframework.http.MediaType.APPLICATION_JSON;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@WebAppConfiguration
@Slf4j
public class FileControllerTest {

    private MockMvc mockMvc;



    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Mockito.reset(fileServiceMock);

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }



    @Test
    public void shouldHealthControllerWorks() throws Exception {
        mockMvc.perform(get("/api/appContext")).andExpect(content().contentType("text/plain;charset=ISO-8859-1")).andExpect(status().isOk());
    }
    @Test
    public void shouldCarWork() throws Exception {
        this.mockMvc.perform(get("/api/car/name/{name}", "opel")).andDo(print()).andExpect(status().isOk());
    }

  
    @Test
    public void shouldCarWork2() throws Exception {
        this.mockMvc.perform(get("/api/car/name/{name}", "opel").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.age").value("23"))
        .andExpect(jsonPath("$.name").value("opel"));
    }
    
    @Test
    public void shouldCarWork3() throws Exception {
        this.mockMvc.perform(get("/api/car/name/{name}", "opel")).andExpect(content().contentType("application/json"));
        String contentAsString = mockMvc.perform(get("/api/car/name/{name}", "opel").accept(APPLICATION_JSON)).andReturn().getResponse().getContentAsString();
        JsonPath jsonPath = new JsonPath(contentAsString);
        assertThat(jsonPath.getString("name"), equalTo("opel"));
        assertThat(jsonPath.getString("age"), equalTo("23"));
    }
    
    
}