package NhtK23cnt2.prj3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "NhtK23cnt2.prj3.entity")
@EnableJpaRepositories(basePackages = "NhtK23cnt2.prj3.repository")
public class Project3Application {

    public static void main(String[] args) {
        SpringApplication.run(Project3Application.class, args
        );
    }
}
