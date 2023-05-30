package g45_lexicon.teat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class TeatApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeatApplication.class, args);
    }

}
