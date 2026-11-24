package medclinic;

import medclinic.persistencia.Usuario;
import medclinic.persistencia.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ServletComponentScan
public class MedClinicApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedClinicApplication.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UsuarioRepository usuarioRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(encoder.encode("admin123"));
                admin.setRole("ADMIN");
                usuarioRepository.save(admin);
            }
            if (usuarioRepository.findByUsername("user").isEmpty()) {
                Usuario user = new Usuario();
                user.setUsername("user");
                user.setPassword(encoder.encode("user123"));
                user.setRole("USER");
                usuarioRepository.save(user);
            }
        };
    }
}