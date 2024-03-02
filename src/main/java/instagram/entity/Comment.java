package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
@Getter  @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "comment_seq", allocationSize = 1)
public class Comment extends IdGenerator{
    @Column(length = 500)
    private String comment;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "comment")
    private List<Like> likes;
    @ManyToOne
    private Post post;

    @Override
    public String toString() {
        return "Comment{" +
                "comment='" + comment + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
