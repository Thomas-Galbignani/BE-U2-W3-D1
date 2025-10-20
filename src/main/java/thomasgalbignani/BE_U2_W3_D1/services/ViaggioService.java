package thomasgalbignani.BE_U2_W3_D1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_W3_D1.entities.Viaggio;
import thomasgalbignani.BE_U2_W3_D1.exceptions.NotFoundException;
import thomasgalbignani.BE_U2_W3_D1.payloads.ViaggioDTO;
import thomasgalbignani.BE_U2_W3_D1.repositories.ViaggioRepository;

import java.util.UUID;

@Service
@Slf4j
public class ViaggioService {

    @Autowired
    private ViaggioRepository viaggioRepository;


    public Viaggio create(ViaggioDTO payload) {
        Viaggio nuovoViaggio = new Viaggio();
        nuovoViaggio.setDestinazione(payload.getDestinazione());
        nuovoViaggio.setDataViaggio(payload.getDataViaggio());
        nuovoViaggio.setStato(payload.getStato());

        return viaggioRepository.save(nuovoViaggio);
    }


    public Page<Viaggio> findAll(Pageable pageable) {
        return viaggioRepository.findAll(pageable);
    }


    public Viaggio findById(UUID id) {
        return viaggioRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }


    public Viaggio findByIdAndUpdate(UUID id, ViaggioDTO payload) {
        Viaggio found = this.findById(id);

        found.setDestinazione(payload.getDestinazione());
        found.setDataViaggio(payload.getDataViaggio());
        found.setStato(payload.getStato());


        return viaggioRepository.save(found);
    }


    public void findByIdAndDelete(UUID id) {
        Viaggio found = this.findById(id);
        viaggioRepository.delete(found);
    }
}