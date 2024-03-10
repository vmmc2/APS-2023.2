package br.com.viviburguer.accountregister;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.viviburguer.*")
@ComponentScan(basePackages = { "br.com.viviburguer.*" })
@EntityScan("br.com.viviburguer.*")
public class AccountRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountRegisterApplication.class, args);
	}

}
