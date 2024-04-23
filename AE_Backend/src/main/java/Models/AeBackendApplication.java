package Models ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("Repositories")
@ComponentScan(basePackages = {"Controllers","Services"})
public class AeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(AeBackendApplication.class, args);
	}

}
