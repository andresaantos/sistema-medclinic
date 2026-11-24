package medclinic.persistencia;

import jakarta.persistence.*;

@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paciente;

    @Column(nullable = false)
    private String medico;

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false)
    private String dataConsulta;

    @Column(nullable = false)
    private double valor;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPaciente() { return paciente; }
    public void setPaciente(String paciente) { this.paciente = paciente; }

    public String getMedico() { return medico; }
    public void setMedico(String medico) { this.medico = medico; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getDataConsulta() { return dataConsulta; }
    public void setDataConsulta(String dataConsulta) { this.dataConsulta = dataConsulta; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
}