package top.dwy.week1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 * @author DWY
 */
@SpringBootApplication
@RestController
public class Application {
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class,args);
        System.out.println("http://localhost:8081/hello");
    }

    @GetMapping("/hello")
    public String getHello() {
        return "hello world";
    }
}