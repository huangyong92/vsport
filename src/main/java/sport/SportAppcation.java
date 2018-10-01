package sport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "sport.domain.repository")
@SpringBootApplication
public class SportAppcation {

    public static void main(String[] args)
    {
        SpringApplication.run(SportAppcation.class, args);
    }
}
