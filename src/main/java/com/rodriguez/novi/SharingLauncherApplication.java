package com.rodriguez.novi;

import com.rodriguez.novi.entities.UserProfileEntity;
import com.rodriguez.novi.repositories.UsersProfileRepository;
import com.rodriguez.novi.services.CalculadoraServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.rodriguez.novi.services.CalculadoraServices;

import java.time.LocalDate;

@SpringBootApplication //This bean scan all the maps to find object build in string that going to be injected in the project
public class SharingLauncherApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(SharingLauncherApplication.class, args);

		UsersProfileRepository repository = context.getBean(UsersProfileRepository.class);
		CalculadoraServices calculadoraServices = new CalculadoraServices();

		//CRUD METHOD
		//Create user
		UserProfileEntity user0 = new UserProfileEntity(null, "Edwin", "Rodriguez Valle", "Male", LocalDate.of(1978, 8,  10), "Camera Obscuradreeft 45", "rodriguez.valle.edwin@gmail.com", "Spanish", true, 638497646,  "It professional" );
		UserProfileEntity user1 = new UserProfileEntity(null, "Jose", "Gomez", "Male", LocalDate.of(1978, 8,  10), "Camera Obscuradreeft 45", "rodriguez.valle.edwin@gmail.com", "Spanish", true, 638497646,  "It professional" );
		UserProfileEntity user2 = new UserProfileEntity(null, "Ronald", "Perez", "Male", LocalDate.of(1978, 8,  10), "Camera Obscuradreeft 45", "rodriguez.valle.edwin@gmail.com", "Spanish", true, 638497646,  "It professional" );
		UserProfileEntity user3 = new UserProfileEntity(null, "Juan", "Gonzales", "Male", LocalDate.of(1978, 8,  10), "Camera Obscuradreeft 45", "rodriguez.valle.edwin@gmail.com", "Spanish", true, 638497646,  "It professional" );

		System.out.println("This is the size of the database User Profile: " + repository.findAll().size());

		//save user
		repository.save(user0);
		repository.save(user1);
		repository.save(user2);
		repository.save(user3);

		System.out.println("This is the size of the database User Profile: " + repository.findAll().size());

		// update users
		repository.findAll();

		//delete users
//		repository.deleteById(1L);

		System.out.println("This is the size of the database User Profile: " + repository.findAll().size());
		int edad = LocalDate.now().getYear() - user2.getBirthday().getYear();
		System.out.println("la edad de " + " " + user2.getLastname() + " es de "  +  edad + " anos" );

		int anoviejo = calculadoraServices.ageCalculator(user2);
		System.out.println(user2.getName() + " tiene " + anoviejo + " anos de edad.");


	}



}
