package pl.java.scalatech.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configurable
@ComponentScan(basePackages="pl.java.scalatech.service",useDefaultFilters=false,includeFilters={@Filter(Service.class)})
@Slf4j
public class ServiceConfig {

   /* @Autowired
    private UserRepository userRepository;

    
    @PostConstruct
    public void init(){
        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info(" {}",userRepository.save(new User("borowiec","przodownik",new BigDecimal(100))));
        log.info(" {}",userRepository.save(new User("borowiec","aga",new BigDecimal(10))));
        log.info(" {}",userRepository.save(new User("borowiec","kalina",new BigDecimal(30))));
        log.info(" {}",userRepository.save(new User("tyson","iron mike",new BigDecimal(3000))));
        log.info(" {}",userRepository.save(new User("rossi","the doctor",new BigDecimal(2000))));
    }*/
}
