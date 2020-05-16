package com.apazon.identity.fb.ws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.apazon.identity.fb.dto.ContactDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class SocialController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	private ObjectMapper objectMapper;
	
	public SocialController() {
		this.objectMapper = new ObjectMapper();
	}
	
	@GetMapping("/user/search")
	public List<ContactDTO> getUserByName(@RequestParam(required=true) String name) {
		List<ContactDTO> contactList = new ArrayList<ContactDTO>();
		
		String response = restTemplate.getForObject("https://www.instagram.com/web/search/topsearch/?context=blended&query="+name, String.class);
	
		try {
			JsonNode content = this.objectMapper.readTree(response).get("users");
			if (content.isArray()) {
		        for (JsonNode arrayItem : content) {
		            contactList.add(this.objectMapper.treeToValue(arrayItem.get("user"), ContactDTO.class));
		        }
		    }
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return contactList;
	}
	
	
}