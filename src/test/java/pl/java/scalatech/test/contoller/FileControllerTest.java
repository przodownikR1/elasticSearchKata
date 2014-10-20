package pl.java.scalatech.test.contoller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.restassured.path.json.JsonPath;

import pl.java.scalatech.config.AppConfig;
import pl.java.scalatech.config.ServiceConfig;
import pl.java.scalatech.config.elastic.ElasticSearchConfig;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
import pl.java.scalatech.service.UserService;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
@ActiveProfiles( value="local")
@WebAppConfiguration
@Slf4j
public class FileControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // Mockito.reset(fileServiceMock);
        userRepository.deleteAll();
        userRepository.save(User.builder().login("malysz").name("adam").salary(new BigDecimal(306)).build());
        log.info(" {}", userRepository.save(User.builder().login("borowiec1").name("przodownik").salary(new BigDecimal(100)).build()));
        log.info(" {}", userRepository.save(User.builder().login("borowiec2").name("aga").salary(new BigDecimal(10)).build()));
        log.info(" {}", userRepository.save(User.builder().login("borowiec3").name("kalina").salary(new BigDecimal(30)).build()));
        log.info(" {}", userRepository.save(User.builder().login("tyson").name("iron mike").salary(new BigDecimal(3234)).build()));
        log.info(" {}", userRepository.save(User.builder().login("rossi").name("the doctor").salary(new BigDecimal(2000)).build()));

        log.info(" {}", userRepository.save(new User("kazimierczak", "juz", new BigDecimal(100))));
        log.info(" {}", userRepository.save(new User("aleksandrowicz", "dawid", new BigDecimal(10))));
        log.info(" {}", userRepository.save(new User("barszcz", "mariusz", new BigDecimal(30))));
        log.info(" {}", userRepository.save(new User("bogadanski", "pawel", new BigDecimal(3000))));
        log.info(" {}", userRepository.save(new User("chudzikowska", "sylwia", new BigDecimal(2000))));

        log.info(" {}", userRepository.save(new User("swietojanski", "przemyslaw", new BigDecimal(100))));
        log.info(" {}", userRepository.save(new User("zurek", "marcin", new BigDecimal(10))));
        log.info(" {}", userRepository.save(new User("grabowski", "michal", new BigDecimal(30))));
        log.info(" {}", userRepository.save(new User("gilewski", "piotr", new BigDecimal(3000))));
        log.info(" {}", userRepository.save(new User("ostroski", "krzych", new BigDecimal(2000))));
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
    public void shouldElasticSearchUserApiWork() throws Exception{
        this.mockMvc.perform(get("/api/user/login/{login}", "malysz")).andExpect(status().isOk());
    }
    
    @Test
    public void shouldCarWork2() throws Exception {
        this.mockMvc.perform(get("/api/car/name/{name}", "opel").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.age").value("23")).andExpect(jsonPath("$.name").value("opel"));
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