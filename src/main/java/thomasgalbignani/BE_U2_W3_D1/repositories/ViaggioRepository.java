package thomasgalbignani.BE_U2_W3_D1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thomasgalbignani.BE_U2_W3_D1.entities.Viaggio;

import java.util.UUID;

@Repository
public interface ViaggioRepository extends JpaRepository<Viaggio, UUID> {
}