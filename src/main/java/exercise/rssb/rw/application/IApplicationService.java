package exercise.rssb.rw.application;

import exercise.rssb.rw.model.Customer;
import exercise.rssb.rw.model.UploadedDataDTO;
import exercise.rssb.rw.model.ValidationFailure;
import exercise.rssb.rw.util.Response;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * The Interface of IApplicationService.
 *
 * @author Christian Iradukunda
 */
public interface IApplicationService {

	Response<List<Customer>> getCustomers();
	Response<List<ValidationFailure>> getFailedCustomers();
	Response<UploadedDataDTO> processFile(MultipartFile file);

	Response<String> persistCustomers();
}