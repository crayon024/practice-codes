package self.practice.codes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import self.practice.codes.sql.multiDataSource.config.PropertyConfiguration;

@SpringBootApplication
(exclude = {DataSourceAutoConfiguration.class})
@Import({PropertyConfiguration.class})
public class CodesApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodesApplication.class, args);
    }

}
