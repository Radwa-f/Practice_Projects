package gestion_compte.gestion_compte.ws;

import gestion_compte.gestion_compte.entities.Compte;
import gestion_compte.gestion_compte.entities.TypeCompte;
import gestion_compte.gestion_compte.repositories.CompteRepository;

import jakarta. jws.WebMethod;
import jakarta. jws.WebParam;
import jakarta. jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@WebService (serviceName = "BanqueWS")
public class CompteSoapService {

    @Autowired
    private CompteRepository compteRepository;

    @WebMethod
    public List<Compte> getComptes () {
        return compteRepository.findAll();
    }

    @WebMethod
    public Compte getCompteById (@WebParam (name = "id") Long id) {
        return compteRepository.findById (id) .orElse (null) ;
    }

    @WebMethod
    public Compte createCompte (@WebParam(name = "solde") double solde,
                                @WebParam (name = "type") TypeCompte type) {
        Compte compte = new Compte(null, solde, new Date(), type);
        return compteRepository.save (compte) ;
    }
    @WebMethod
    public Compte updateCompte(
            @WebParam(name = "id") Long id,
            @WebParam(name = "solde") double solde,
            @WebParam(name = "dateCreation") Date dateCreation,
            @WebParam(name = "type") TypeCompte type) {

        Optional<Compte> existingCompte = compteRepository.findById(id);

        if (existingCompte.isPresent()) {
            Compte compteToUpdate = existingCompte.get();
            compteToUpdate.setSolde(solde);
            compteToUpdate.setDateCreation(dateCreation);
            compteToUpdate.setType(type);

            return compteRepository.save(compteToUpdate);
        }

        return null;
    }

    @WebMethod
    public boolean deleteCompte (@WebParam (name = "id") Long id) {
        if (compteRepository.existsById(id)) {
            compteRepository.deleteById(id) ;
            return true;
        }
        return false;
}
}
