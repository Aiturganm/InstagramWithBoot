package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "img_seq", allocationSize = 1)
public class Image extends IdGenerator{
    @Column(name = "image_url", length = 1000)
    private String imageUrl;
    @OneToMany(mappedBy = "image")
    private List<User> users;
    @ManyToOne
    private Post post;

    public Image(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
