package pl.java.scalatech.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

/**
 * @author Sławomir Borowiec
 *         Module name : bootSetting
 *         Creating time : 17 wrz 2014 09:49:48
 */
@RestController
@Slf4j
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
public class HelloController {
    List<Car> cars = Lists.newArrayList(new Car("bmw","10"),new Car("star","12"),new Car("opel","23"));
    
    Random rand = new Random();

    @RequestMapping("/hello")
    public String hello() {
        return "hello ...";
    }

    @RequestMapping("/car")
    public Car car() {
        return new Car("bmw", "10");
    }

    @RequestMapping("/car/name/{name}")
    Car getCarByName(@PathVariable("name")String name) {
        Optional<Car> opt = cars.stream().filter(car -> name.equals(car.name)).findFirst();
        if(opt.isPresent()){
            return opt.get();
        }
        throw new IllegalArgumentException("car with name "+ name+ " not exists");
    }
    
    @Data
    @AllArgsConstructor
    public class Car {

        private String name;

        private String age;
    }
}
