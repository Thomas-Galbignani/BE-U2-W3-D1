package thomasgalbignani.BE_U2_W3_D1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import thomasgalbignani.BE_U2_W3_D1.entities.Dipendente;
import thomasgalbignani.BE_U2_W3_D1.exceptions.UnauthorizedException;
import thomasgalbignani.BE_U2_W3_D1.payloads.LoginDTO;
import thomasgalbignani.BE_U2_W3_D1.security.JWTTools;

@Service
public class AuthService {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private JWTTools jwtTools;

    public String CheckAndCreate(LoginDTO bodyLogin) {
        // Controllo sulle credenziali: verifico nel DB che esista un utente con qull'email e poi nel caso esista, verifico la password
        Dipendente dipendenteFound = this.dipendenteService.findDipendenteByEmail(bodyLogin.email());

        if (dipendenteFound.getPassword().equals(bodyLogin.password())) {
            return jwtTools.createToken(dipendenteFound);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

}
