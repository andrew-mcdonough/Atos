package com.qikserve.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.qikserve.supermarket.pojo.CheckoutTotal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootApplicationTests {
	@LocalServerPort
	int randomServerPort;

	@Test
	public void testGetEmployeeListSuccessTest() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8081/products";
		URI uri = new URI(baseUrl);

		ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());
		Assert.assertEquals(true, result.getBody().contains("Amazing"));
	}

	@Test
	public void testGetCheckout() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		final String baseUrl = "http://localhost:8085/qikserve/checkout/Andrew";
		URI uri = new URI(baseUrl);

		ResponseEntity<CheckoutTotal> result = restTemplate.getForEntity(uri, CheckoutTotal.class);

		// Verify request succeed
		Assert.assertEquals(200, result.getStatusCodeValue());

		Assert.assertEquals(true, result.getBody().getProductId().contains("Amazing Burger"));
		Assert.assertEquals(true, result.getBody().getProductId().contains("Boring Fries"));
		Assert.assertEquals(true, result.getBody().getProductId().contains("Amazing Pizza"));
		Assert.assertEquals(true, result.getBody().getRawPrice().toString().contains("2297"));
		Assert.assertEquals(true, result.getBody().getTotalPayable().toString().contains("2297"));
		Assert.assertEquals(true, result.getBody().getTotalPromos().toString().contains("0"));
	}

}
