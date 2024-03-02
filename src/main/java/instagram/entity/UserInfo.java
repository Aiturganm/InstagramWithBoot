package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "info_seq", allocationSize = 1)
public class UserInfo extends IdGenerator{
    @Column(name = "full_name")
    private String fullName;
    @Column(length = 1000)
    private String biography;
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;
    @Column(length = 1000)
    private String image;

    public UserInfo(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "fullName='" + fullName + '\'' +
                ", biography='" + biography + '\'' +
                ", gender=" + gender +
                ", image='" + image + '\'' +
                '}';
    }
}
