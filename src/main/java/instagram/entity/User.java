package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter    @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "user_seq", allocationSize = 1)
public class User extends IdGenerator{
    @Column(name = "user_name", unique = true)
    private String userName;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private UserInfo userInfo;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Image image;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Follower follower;
    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "user")
    private List<Post> posts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();
    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private Like like = new Like();


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phoneNumber + '\'' +
                '}';
    }
}
