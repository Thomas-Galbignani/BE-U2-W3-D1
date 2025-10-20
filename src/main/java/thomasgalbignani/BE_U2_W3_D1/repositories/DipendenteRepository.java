package thomasgalbignani.BE_U2_W3_D1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thomasgalbignani.BE_U2_W3_D1.entities.Dipendente;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DipendenteRepository extends JpaRepository<Dipendente, UUID> {

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<Dipendente> findByUsername(String username);
    
    Optional<Dipendente> findByEmail(String email);
}
