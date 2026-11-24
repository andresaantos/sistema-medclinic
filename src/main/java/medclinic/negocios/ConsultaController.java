package medclinic.negocios;

import medclinic.persistencia.Consulta;
import medclinic.persistencia.ConsultaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    private final ConsultaRepository consultaRepository;

    public ConsultaController(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @PostMapping
    public Consulta cadastrar(@RequestBody Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    @GetMapping
    public List<Consulta> listar(@RequestParam(required = false) String paciente) {
        if (paciente != null && !paciente.isBlank()) {
            return consultaRepository.findByPacienteContainingIgnoreCase(paciente);
        }
        return consultaRepository.findAll();
    }

    @GetMapping("/relatorio")
    public Object gerarRelatorio() {
        List<Consulta> todas = consultaRepository.findAll();
        double faturamentoTotal = todas.stream().mapToDouble(Consulta::getValor).sum();

        Map<String, Long> porEspecialidade = todas.stream()
                .collect(Collectors.groupingBy(Consulta::getEspecialidade, Collectors.counting()));

        return Map.of(
                "totalConsultas", todas.size(),
                "faturamentoTotal", faturamentoTotal,
                "porEspecialidade", porEspecialidade
        );
    }
}