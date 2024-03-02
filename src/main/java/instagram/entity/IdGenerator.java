package instagram.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter    @Setter
public class IdGenerator {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id_gen"
    )
    private Long id;

    @Override
    public String toString() {
        return "IdGenerator{" +
                "id=" + id +
                '}';
    }
}
