package pl.java.scalatech.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.io.Serializable;
import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.java.scalatech.domain.PKNature;
import pl.java.scalatech.service.common.PaginationService;

import com.google.common.base.Preconditions;

@RequestMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
@Slf4j
public abstract class CrudController<T extends PKNature<K>, K extends Serializable> {

    protected final PaginationService<T, K> service;

    @Autowired
    protected MessageSource messageSource;

    protected Locale locale = Locale.getDefault();

    public CrudController(PaginationService<T, K> paginationService) {
        this.service = paginationService;

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PagedResources<Resource<T>>> getResources(@PageableDefault(size = 5) Pageable pageable, PagedResourcesAssembler<T> assembler) {
        Page<T> items = service.findPageByCondition(pageable);
        return new ResponseEntity<>(assembler.toResource(items), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getResourceById(@PathVariable("id") K id, @MatrixVariable Optional<Map<String, String>> maps) {
        log.info("+++  getResourceById {}", id);
        T item = service.findById(id);
        //if(maps.isPresent()){
          log.info("+++++       id={} , matrixVars={} ", id, maps);
       // }
       
        return getRightResponseEntity(item);
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<Void> createResource(@RequestBody T t) {
        T newT = service.persist(t);
        ResourceAssemblerSupport<T, ?> ras = getRas(t);
        HttpHeaders httpHeaders = new HttpHeaders();
        String newResourceLink = ras.toResource(newT).getLink("self").getHref();
        httpHeaders.setLocation(URI.create(newResourceLink));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") K id) {
        T loadedT = service.findById(id);
        loadedT = Preconditions.checkNotNull(loadedT);
        service.delete(loadedT);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id:\\d+}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateResource(@PathVariable K id, @RequestBody @Valid final T resource) {
        T loadedT = service.findById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(linkTo(methodOn(getClass()).getResourceById(id, null)).toUri());
        return new ResponseEntity<>(getRas(loadedT), headers, HttpStatus.NO_CONTENT);
    }

    protected HttpHeaders addCORSHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE");
        headers.add("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
        return headers;

    }
    
    @RequestMapping(value="/**", method=RequestMethod.OPTIONS)
    public HttpEntity<?> handleOptionsRequest() {
    // a CORS preflight request will be handled by our interceptor
    HttpHeaders headers = new HttpHeaders();
    headers.add("Allow","GET, HEAD, POST, PUT, OPTIONS");
    return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    protected abstract ResponseEntity<?> getRightResponseEntity(T t);

    protected abstract ResourceAssemblerSupport<T, ?> getRas(T t);
}
