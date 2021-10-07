package exercise.rssb.rw.application;

import exercise.rssb.rw.model.Customer;
import exercise.rssb.rw.model.UploadedDataDTO;
import exercise.rssb.rw.model.ValidationFailure;
import exercise.rssb.rw.repository.ICustomerRepository;
import exercise.rssb.rw.util.Response;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Class ApplicationService.
 *
 * @author Christian Iradukunda
 */
@Service
public class ApplicationService implements IApplicationService {

	@Autowired
	private ICustomerRepository repository;
	private List<Customer> customers = new ArrayList<>();
	private List<ValidationFailure> failedValidations = new ArrayList<>();

	@Override
	public Response<List<Customer>> getCustomers() {
		return new Response<>(customers);
	}

	@Override
	public Response<List<ValidationFailure>> getFailedCustomers() {
		return new Response<>(failedValidations);
	}

	@Override
	public Response<UploadedDataDTO> processFile(MultipartFile file) {
		try (XSSFWorkbook wb = new XSSFWorkbook(file.getInputStream())){
			System.out.println(file.getContentType());
			System.out.println(file.getName());
			System.out.println(file.getOriginalFilename());
			System.out.println(file.getResource());

/*			Workbook workbook = StreamingReader.builder().rowCacheSize(100) // number of rows to keep in memory
					.bufferSize(4096) // index of sheet to use (defaults to 0)
					.open(file);*/


			XSSFSheet sheet = wb.getSheetAt(0);

			int lastIndex = sheet.getLastRowNum();
			processData(sheet, 1, lastIndex);
//			ExecutorService es = Executors.newCachedThreadPool();
/*			if(lastIndex > 5000){
				for(int i = 1; i <= lastIndex; i+=5000){

					System.out.println(i);
					int j = i+5000;
						j= Math.min(j, lastIndex);

					int finalI = i;
					int finalJ = j;
					 new Thread(() -> {
						processData(sheet, finalI, finalJ);
					}).start();

				}
			}*/
			System.out.println("customers size : " + customers.size());
			System.out.println("failed customers size : " + failedValidations.size());
			UploadedDataDTO data = new UploadedDataDTO(customers, failedValidations);
			return new Response<>(data);
		} catch (Exception ex){
			ex.printStackTrace();
			return new Response<>(ex);
		}
	}

	private void processData(XSSFSheet sheet, Integer startingIndex, Integer endingIndex){


		Iterator<Row> rowIterator = sheet.rowIterator();
		int i=1;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			String names = row.getCell(0).getStringCellValue();

			String nid = row.getCell(1).getStringCellValue();

			String phoneNumber = row.getCell(2).getStringCellValue();

			String gender = row.getCell(3).getStringCellValue();

			String email = row.getCell(4).getStringCellValue();
			List<String> errorMessages = new ArrayList<>();
			if(validateCustomerData(names, nid, phoneNumber, gender, email, errorMessages)){
				Customer customer = new Customer();
				customer.setFullName(names);
				customer.setIdentificationNumber(nid);
				customer.setPhoneNumber(phoneNumber);
				customer.setGender(gender);
				customer.setEmail(email);
				customers.add(customer);
			} else {
				ValidationFailure validationFailure = new ValidationFailure(i, names, gender, phoneNumber, email, nid);
				validationFailure.setErrorMessages(errorMessages);
				failedValidations.add(validationFailure);
			}
			i++;
		}

	}

	private boolean validateCustomerData(String names, String nid, String phoneNumber, String gender, String email,
			List<String> errorMessages){
		boolean failed = false;
		if(names == null || names.isEmpty()){
			errorMessages.add("Names are invalid");
			failed = true;
		}
		if(nid == null || nid.length() != 16 || !NumberUtils.isCreatable(nid)){
			errorMessages.add("Nid has errors.");
			failed = true;
		}
		if(phoneNumber == null || !phoneNumber.matches("(07)(2|3|8|9)(\\d{7})")){
			errorMessages.add("Phone number is not valid");
			failed = true;
		}
		if(gender == null || !gender.matches("M|F")){
			errorMessages.add("gender value is not valid");
			failed = true;
		}
		if(email == null || !email.matches("^(.+)@(.+)\\.(.+)$")){
			errorMessages.add("Email is invalid");
			failed = true;
		}
		return !failed;
	}

	@Override
	public Response<String> persistCustomers() {
		try {
			repository.saveAll(customers);
			return new Response<>("Number of persisted customers: " +customers.size());
		}catch (Exception ex){
			return new Response<>(ex);
		}
	}

}