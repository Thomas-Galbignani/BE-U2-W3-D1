package thomasgalbignani.BE_U2_W3_D1.controllers;

//Viene creato un controller per due endpoint che useranno l'autenticazione:
// - Login
// - Register

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import thomasgalbignani.BE_U2_W3_D1.entities.Dipendente;
import thomasgalbignani.BE_U2_W3_D1.exceptions.ValidationException;
import thomasgalbignani.BE_U2_W3_D1.payloads.DipendenteDTO;
import thomasgalbignani.BE_U2_W3_D1.payloads.LoginDTO;
import thomasgalbignani.BE_U2_W3_D1.payloads.LoginResponseDTO;
import thomasgalbignani.BE_U2_W3_D1.services.AuthService;
import thomasgalbignani.BE_U2_W3_D1.services.DipendenteService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private DipendenteService dipendenteService;
    @Autowired
    private AuthService authService;

    //1.
    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO loginBody) {
        return new LoginResponseDTO(this.authService.CheckAndCreate(loginBody));
    }

    //2.
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente createDipendente(@RequestBody @Validated DipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }

        return this.dipendenteService.save(body);
    }

}
