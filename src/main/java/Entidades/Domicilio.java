package Entidades;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Domicilio")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Domicilio implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NombreCalle")
    private String nombreCalle;
    @Column(name = "NumeroCalle")
    private int numero;
    @OneToOne(mappedBy = "domicilio")
    private Cliente cliente;

}
