package gestion_compte.gestion_compte;

import gestion_compte.gestion_compte.entities.Compte;
import gestion_compte.gestion_compte.entities.TypeCompte;
import gestion_compte.gestion_compte.repositories.CompteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class GestionCompteApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCompteApplication.class, args);
	}

	@Bean
	CommandLineRunner start (CompteRepository compteRepository){
		return args -> {
			compteRepository.save (new Compte(null, Math. random () *9000, new Date(),
				TypeCompte. EPARGNE)) ;
			compteRepository.save (new Compte(null, Math. random ()*9000, new Date(),
					TypeCompte. COURANT));
			compteRepository.save(new Compte(null, Math. random ()*9000, new Date() ,
					TypeCompte. EPARGNE)) ;
			compteRepository.findAll() .forEach(c -> {
				System.out.println(c.toString());
			});
			};
		}
}


