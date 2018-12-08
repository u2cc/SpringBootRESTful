package com.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Greeting;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * Controller class for all the endpoints configruations
 * @author James Chen
 *
 */
@Api(value="RESTful Endpoints")
@RestController
public class SpringBootRestfulController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootRestfulController.class);
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    /**
     * The above example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps all HTTP operations by default. 
     * Use @RequestMapping(method=GET) to narrow this mapping.
     * This endpoint handles in-path requet parameters instead of JSON therefore the use of @RequestParam
     * @param name
     * @return
     */
    @ApiOperation(value = "Greet everyone")
    @RequestMapping(path="/greeting", method=RequestMethod.GET)
    public Greeting greeting(@ApiParam(value = "Name to be used in the response")@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.info("Receiving name variabe: "+name+".");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    public Greeting greetingJSON() {
    	return null;
    }
}
