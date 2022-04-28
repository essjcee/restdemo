package com.example.music;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class MusicApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void testAllItems() {
		ResponseEntity<List<MusicItem>> responseEntity = restTemplate.exchange(
				"/items",
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<MusicItem>>() {});

		List<MusicItem> responseBody = responseEntity.getBody();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(3, responseBody.size());
	}

	@Test
	void testPost() throws JSONException {
		MusicItem testItem = new MusicItem("Madness","Madness","Ska");
		testItem.setId(0l);
		HttpEntity<MusicItem> requestEntity = new HttpEntity<>(testItem);
		ResponseEntity<MusicItem> responseEntity = restTemplate.postForEntity("/items",requestEntity,MusicItem.class);
		assertEquals(HttpStatus.CREATED,responseEntity.getStatusCode());
		assertEquals("Madness",responseEntity.getBody().getName());
	}
}
