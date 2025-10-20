package thomasgalbignani.BE_U2_W3_D1.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_W3_D1.entities.Dipendente;
import thomasgalbignani.BE_U2_W3_D1.entities.Prenotazione;
import thomasgalbignani.BE_U2_W3_D1.entities.Viaggio;
import thomasgalbignani.BE_U2_W3_D1.exceptions.BadRequestException;
import thomasgalbignani.BE_U2_W3_D1.exceptions.NotFoundException;
import thomasgalbignani.BE_U2_W3_D1.payloads.PrenotazioneDTO;
import thomasgalbignani.BE_U2_W3_D1.repositories.DipendenteRepository;
import thomasgalbignani.BE_U2_W3_D1.repositories.PrenotazioneRepository;
import thomasgalbignani.BE_U2_W3_D1.repositories.ViaggioRepository;

import java.time.LocalDate;

@Service
@Slf4j
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggioRepository viaggioRepository;


    public Prenotazione create(PrenotazioneDTO payload) {
        Dipendente dipendente = dipendenteRepository.findById(payload.getDipendenteId())
                .orElseThrow(() -> new NotFoundException(payload.getDipendenteId()));

        Viaggio viaggio = viaggioRepository.findById(payload.getViaggioId())
                .orElseThrow(() -> new NotFoundException(payload.getViaggioId()));

        LocalDate dataViaggioCorrente = viaggio.getDataViaggio();

        prenotazioneRepository.findByDipendenteIdAndViaggioDataViaggio(
                        dipendente.getId(), dataViaggioCorrente)
                .ifPresent(p -> {
                    throw new BadRequestException("Il dipendente " + dipendente.getId() +
                            " ha già un viaggio prenotato per il giorno " + dataViaggioCorrente +
                            " (Prenotazione ID: " + p.getId() + ")");
                });


        Prenotazione nuovaPrenotazione = new Prenotazione();

        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggio(viaggio);

        nuovaPrenotazione.setDataRichiesta(LocalDate.now());

        nuovaPrenotazione.setNote(payload.getNote());
        nuovaPrenotazione.setPreferenzaVoloOAlloggio(payload.getPreferenzaVoloOAlloggio());

        return prenotazioneRepository.save(nuovaPrenotazione);
    }

    public Page<Prenotazione> findAll(Pageable pageable) {
        return prenotazioneRepository.findAll(pageable);
    }
}