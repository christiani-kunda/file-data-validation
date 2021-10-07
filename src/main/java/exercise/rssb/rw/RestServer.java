package exercise.rssb.rw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"exercise.rssb.rw"}, exclude = { MongoAutoConfiguration.class})
public class RestServer extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestServer.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RestServer.class);
	}
}
