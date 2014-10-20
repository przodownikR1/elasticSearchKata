package pl.java.scalatech.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import pl.java.scalatech.assembler.UserAssembler;
import pl.java.scalatech.assembler.UserResource;
import pl.java.scalatech.domain.User;
import pl.java.scalatech.exception.BadRequestException;
import pl.java.scalatech.rest.dto.ApiError;
import pl.java.scalatech.service.UserService;

@RestController
@RequestMapping(value = UserController.API, produces = { MediaType.APPLICATION_JSON_VALUE })
@Slf4j
public class UserController extends CrudController<User,Long> {
    protected static final String API = "/api/user";
    
    // private final PagedResourcesAssembler<User> pagedResourcesAssembler;
    private final UserAssembler userAssembler;
    private final UserService userService;
    @Autowired
    
    private HateoasPageableHandlerMethodArgumentResolver resolver;

    @Autowired
    public UserController(UserService userService) {
        super(userService);
        this.userService = userService;
        this.userAssembler = new UserAssembler(this.getClass(), UserResource.class);
    }

    @RequestMapping(value = "/login/{login}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserByLogin(@PathVariable("login")String login,Locale locale){
        User user = userService.findByLogin(login);
        if (user == null) { return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("not_exists",
                new Object[] { login }, locale)), HttpStatus.NOT_FOUND); }
        UserResource resource = userAssembler.toResource(user);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/hateoas/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUserById(@PathVariable("id") Long id, Locale locale) {
        User user = userService.findById(id);
        if (user == null) { return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("not_exists",
                new Object[] { id }, locale)), HttpStatus.NOT_FOUND); }
        UserResource resource = userAssembler.toResource(user);
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<?> getRightResponseEntity(User t) {
        if (t == null) { return new ResponseEntity<>(new ApiError(HttpStatus.NOT_FOUND.value(), messageSource.getMessage("not_exists", new Object[] { t },
                locale)), HttpStatus.NOT_FOUND); }
        return new ResponseEntity<>(t, HttpStatus.OK);
    }

    private Link createPaginatedResourceLink(Link link) {
        String href = link.getHref();
        UriComponents components = UriComponentsBuilder.fromUriString(href).build();
        TemplateVariables variables = resolver.getPaginationTemplateVariables(null, components);
        return new Link(new UriTemplate(href, variables), link.getRel());
    }

    @Override
    protected ResourceAssemblerSupport<User, ?> getRas(User user) {
        return this.userAssembler;
    }

    @RequestMapping("/domain/{id}")
    public User showUserForm(@PathVariable("id") User user, Model model) {
        log.info("use ....- > {}", user);
        return user;
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class, BadRequestException.class, NullPointerException.class })
    public void handler(HttpServletRequest req, RuntimeException exception){
     log.error("controller handler", exception);
        
    }
}
