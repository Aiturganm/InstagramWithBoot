package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@NoArgsConstructor
@AllArgsConstructor
@Getter    @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "like_gen", allocationSize = 1)
public class Like extends IdGenerator{
    private Boolean isLike;
    @OneToOne(mappedBy = "like")
    private User user;
    @ManyToOne
    private Comment comment;
    @ManyToOne
    private Post post;
    @Override
    public String toString() {
        return "Like{" +
                "isLike=" + isLike +
                '}';
    }
}