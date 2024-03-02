package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
@Getter  @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "post_seq", allocationSize = 1)
public class Post extends IdGenerator{
    private String title;
    @Column(length = 1500)
    private String description;
    private LocalDate createdAt;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Comment> comments;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<Like> likes = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.REMOVE,CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Image> images;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;


    public void setImage(Image image){
        this.images = new ArrayList<>();
        if(!this.images.isEmpty()) {
            this.images.add(image);
        }else {
            this.images = new ArrayList<>();
            this.images.add(image);
        }
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
