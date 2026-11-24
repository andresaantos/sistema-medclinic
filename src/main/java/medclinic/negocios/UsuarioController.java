package medclinic.negocios;

import medclinic.persistencia.Usuario;
import medclinic.persistencia.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public Object listar() {
        return usuarioRepository.findAll().stream()
                .map(u -> Map.of(
                        "id", u.getId(),
                        "username", u.getUsername(),
                        "role", u.getRole()
                ))
                .toList();
    }

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Usuario novoUsuario) {
        if (usuarioRepository.findByUsername(novoUsuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("erro", "Usuário já existe"));
        }
        novoUsuario.setPassword(encoder.encode(novoUsuario.getPassword()));
        usuarioRepository.save(novoUsuario);
        return ResponseEntity.ok(Map.of("mensagem", "Usuário cadastrado com sucesso"));
    }
}