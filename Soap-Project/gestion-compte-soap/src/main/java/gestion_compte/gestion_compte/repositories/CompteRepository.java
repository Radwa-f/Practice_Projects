package gestion_compte.gestion_compte.repositories;

import gestion_compte.gestion_compte.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {

}
