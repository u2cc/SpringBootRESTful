package com.rest.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.rest.entities.DiecastCar;
import com.rest.exception.DiecastCarAlreadyExistsException;
import com.rest.exception.DiecastCarNotFoundException;
import com.rest.services.DiecastCarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rest.model.Greeting;
import com.rest.model.User;

//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;

/**
 * Controller class for all the endpoints configurations
 * http://localhost:{server.port}/{springdoc.swagger-ui.path}
 * http://localhost:{server.port}/{springdoc.api-docs.path}
 *
 * @author James Chen
 *
 */
//@Api(value="RESTful Endpoints")
@RestController
public class SpringBootRestfulController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootRestfulController.class);
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private DiecastCarService diecastCarService;

    
    /**
     * The above example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps all HTTP operations by default. 
     * Use @RequestMapping(method=GET) to narrow this mapping.
     * This endpoint handles in-path requet parameters instead of JSON therefore the use of @RequestParam
     * @param name
     * @return
     */
   // @ApiOperation(value = "Greet everyone")
    @Operation(summary = "Greet everyone")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Greet the name given",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = " Page Not Found",
                    content = @Content)
    })
    @RequestMapping(path="/greeting", method=RequestMethod.GET)
    public Greeting greeting(//@ApiParam(value = "Name to be used in the response")
                                 @Parameter(description = "Name to be greeted.") @RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.info("Receiving name variabe: "+name+".");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    /**
     * This method consumes JSON object sent in the POST method.
     * The annotation @RequestBody indicates a parameter is bound to the request for Spring to convert 
     * MediaType is explicitly specified for restricting the supported content type
     * @param user
     * @return the user details encapsulated in a user Object.
     */
  //  @ApiOperation(value = "Return user infomation")
    @Operation(summary = "Return user infomation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "Return the user info provided",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = " age Not Found",
                    content = @Content)
    })
    @RequestMapping(path="/userdetails", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)      
    public User getUserDetails(//@ApiParam(value = "User Criteria",required=true)
                                   @RequestBody User user) {
    	LOGGER.info("Receiving payload: " + user.toString());
    	return user;
    }

    /**
     * This method returns a list containing all the diecast cars in the DB.
     * @return a list of all the diecast cars
     */
   // @ApiOperation(value="List all diecast cars")
    @Operation(summary = "List all diecast cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all the cars from database",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page Not Found",
                    content = @Content)
    })
    @RequestMapping(path="/getAllDiecastCars", method=RequestMethod.GET)
    public List<DiecastCar> getAllDiecastCars(){
        LOGGER.info("Fetching all diecast cars");
        return diecastCarService.list();
    }

    /**
     * This method searches all the diecast cars from the brands in the given list
     * @param brands List of brands of the diecast cars
     * @return a list of all the diecast cars from the brands given
     */
   // @ApiOperation(value="Find all cars by brands")
    @Operation(summary = "Find all cars by brands")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "List of all the cars from the given brands",
                    content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404",
                    description = "Page Not Found",
                    content = @Content)
    })
    @RequestMapping(path = "/findDiecastCarsByBrand", method = RequestMethod.GET)
    //@ResponseStatus(HttpStatus.OK)
    public List<DiecastCar> findDiecastCarsByBrands(//@ApiParam(value="List of brands", defaultValue="Matchbox, Tongas")
                                                        @Parameter(description = "Brands to search") @RequestParam(value="brands", defaultValue = "Matchbox, Hot Wheels") List<String> brands){
        return diecastCarService.findByBrands(brands);
    }

    @RequestMapping(path = "/updateDiecastCar", method = RequestMethod.PATCH, consumes = "application/json-patch+json")
    public ResponseEntity<DiecastCar> updateDiecastCar(@RequestParam Long id, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {
        DiecastCar diecastCarPatched = diecastCarService.updateDiecastCar(id, jsonPatch);
        return ResponseEntity.ok(diecastCarPatched);
    }

    @PostMapping(path="/addDiecastCar")
    public void addDiecastCar(@RequestBody DiecastCar diecastCar){
        if(diecastCarService.ifPresent(diecastCar)){
            throw new DiecastCarAlreadyExistsException();
        }
        diecastCarService.saveDieCastCar(diecastCar);
    }

    @DeleteMapping("/deleteDiecastCar/{id}")
    void deleteDiecastCar(@PathVariable Long id) {
        diecastCarService.deleteById(id);
    }


}
