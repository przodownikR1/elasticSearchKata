package pl.java.scalatech.assembler;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import pl.java.scalatech.controller.UserController;
import pl.java.scalatech.domain.User;

public class UserAssembler extends ResourceAssemblerSupport<User, UserResource> {

    public UserAssembler(Class<?> controllerClass, Class<UserResource> resourceType) {
        super(controllerClass, resourceType);

    }

    @Override
    public UserResource toResource(User entity) {
        UserResource ur = new UserResource();
        ur.setName(entity.getName());
        ur.setUserId(entity.getId());
        ur.setSalary(entity.getSalary());
      
        ur.add(linkTo(UserController.class).withRel("all"));
        ur.add(linkTo(methodOn(UserController.class).getUserById(entity.getId(), Locale.getDefault())).withSelfRel());
        //ur.add(linkTo(methodOn(UserController.class).create(new User("test", "ln", new BigDecimal(234)))).withRel("createUser"));
        return ur;

    }

}