package com.apazon.identity.sample.ws;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apazon.identity.sample.greeting.Greeting;
import com.apazon.identity.sample.ws.dto.SpringQuoteDTO;

@RestController
public class GreetingController {

	@Value("${welcome.init}")
    private String welcome;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private static final String template = ", %s!";
	private final AtomicLong counter = new AtomicLong();

	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(welcome+template, name));
	}
	
	@GetMapping("/quote")
	public String getQuote() {
		SpringQuoteDTO quoteDTO= this.restTemplate.getForObject("https://gturnquist-quoters.cfapps.io/api/random", SpringQuoteDTO.class);
		return quoteDTO.getValue().getQuote();
	}
}