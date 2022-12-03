package pl.sda.j133.hibernate.warsztat.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mechanik {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imie;
    private String kwalifikacje;
    private String specjalizacja;

    @OneToMany(mappedBy = "mechanik")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<SerwisPojazdu> zrealizowaneSerwisy;
}
