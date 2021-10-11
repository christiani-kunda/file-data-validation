package exercise.rssb.rw;

import exercise.rssb.rw.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

/**
 * The Class TestApplicationService.
 *
 * @author Christian Iradukunda
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestApplicationService {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	private String getBaseUrl(){
		return "http://localhost:"+ port + "/request";
	}

	@Test
	public void testCreateCustomer(){

		Customer customer = new Customer();
		customer.setFullName("Fun Name");
		customer.setPhoneNumber("07111111111");

		HttpEntity<Customer> request = new HttpEntity<>(customer);

		ResponseEntity<String> responseEntityStr = restTemplate.postForEntity(getBaseUrl() + "/data/list", request, String.class);
		System.out.println(responseEntityStr.getBody());
		Assert.notNull(responseEntityStr.getBody());
	}
}