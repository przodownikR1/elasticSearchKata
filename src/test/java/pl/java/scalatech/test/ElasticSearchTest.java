package pl.java.scalatech.test;

import static org.elasticsearch.index.query.QueryBuilders.queryString;

import java.math.BigDecimal;

import lombok.extern.slf4j.Slf4j;

import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.java.scalatech.config.ElasticSearchConfiguration;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.repository.UserRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = { ElasticSearchConfiguration.class})
@Slf4j
public class ElasticSearchTest {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;
    @Autowired
    private UserRepository userRepository;
    
    public Page<User> findByLogin(String login) {
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryString(login).field("login")).build();
        return elasticsearchTemplate.queryForPage(searchQuery, User.class);
    }
    @Before
    public void init(){
        userRepository.save(User.builder().login("malysz").name("adam").salary(new BigDecimal(306)).build());
        log.info(" {}", userRepository.save(User.builder().login("borowiec").name("przodownik").salary(new BigDecimal(100)).build()));
        log.info(" {}", userRepository.save(User.builder().login("borowiec").name("aga").salary(new BigDecimal(10)).build()));
        log.info(" {}", userRepository.save(User.builder().login("borowiec").name("kalina").salary(new BigDecimal(30)).build()));
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
    }
    @Test
    public void shouldfindByLogin(){
        Page<User> pageUser = findByLogin("krzych");
        Assertions.assertThat(pageUser).hasSize(1).contains(new User("ostroski", "krzych", new BigDecimal(2000)));
        
    }
}
