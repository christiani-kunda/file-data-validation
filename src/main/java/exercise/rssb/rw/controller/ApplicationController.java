package exercise.rssb.rw.controller;

import exercise.rssb.rw.application.IApplicationService;
import exercise.rssb.rw.model.Customer;
import exercise.rssb.rw.model.UploadedDataDTO;
import exercise.rssb.rw.model.ValidationFailure;
import exercise.rssb.rw.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The Class IApplicationController.
 *
 * @author Christian Iradukunda
 */
@RestController
@RequestMapping("request")
public class ApplicationController {

	@Autowired
	private IApplicationService applicationService;

	@GetMapping("/data/list")
	public Response<List<Customer>> listUploadedCustomers(){
		return applicationService.getCustomers();
	}

	@GetMapping("/data/list/failed-validations")
	public Response<List<ValidationFailure>> listFailedValidations(){
		return applicationService.getFailedCustomers();
	}

	@PostMapping("data/save")
	public Response<Customer> createCustomer(){
//		return applicationService.persistCustomer(customer);
		return new Response<>(new Customer());
	}

	@PostMapping("data/upload")
	public Response<UploadedDataDTO> updateCustomer(@RequestParam("file") MultipartFile file){
		return applicationService.processFile(file);
	}

}