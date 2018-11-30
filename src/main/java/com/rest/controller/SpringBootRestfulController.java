package com.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rest.model.Greeting;

@RestController
public class SpringBootRESTfulController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootRESTfulController.class);
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    /**
     * The above example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps all HTTP operations by default. 
     * Use @RequestMapping(method=GET) to narrow this mapping.
     * @param name
     * @return
     */
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	LOGGER.info("Receiving name variabe: "+name+".");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}