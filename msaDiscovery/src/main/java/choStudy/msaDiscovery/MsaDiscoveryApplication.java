package choStudy.msaDiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MsaDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaDiscoveryApplication.class, args);
	}

}
