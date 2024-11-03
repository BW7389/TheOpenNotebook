package com.dev.notebook;

import com.dev.notebook.domain.RequestContext;
import com.dev.notebook.enumeration.Authority;
import com.dev.notebook.models.Role;
import com.dev.notebook.repositories.IRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class NotebookApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotebookApplication.class, args);
	}


	@Bean
	CommandLineRunner commandLineRunner(IRoleRepository iRoleRepository){
		return args -> {
			RequestContext.setUserId(0L);
			if (iRoleRepository.findRoleByName(Authority.USER.name()).isEmpty()) {
				var userRole = new Role();
				userRole.setName(Authority.USER.name());
				userRole.setCode("USER_01");
				userRole.setAuthorities(new Authority[]{Authority.USER});
				iRoleRepository.save(userRole);
			}

			if (iRoleRepository.findRoleByName(Authority.ADMIN.name()).isEmpty()) {
				var adminRole = new Role();
				adminRole.setName(Authority.ADMIN.name());
				adminRole.setCode("ADMIN_01");
				adminRole.setAuthorities(new Authority[]{Authority.ADMIN});
				iRoleRepository.save(adminRole);
			}

			RequestContext.start();

		};
	}

}
