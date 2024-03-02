package instagram.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "followers")
@NoArgsConstructor
@AllArgsConstructor
@Getter  @Setter
@Builder
@SequenceGenerator(name = "id_gen", sequenceName = "follow_gen", allocationSize = 1)
public class Follower extends IdGenerator{
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscribers = new ArrayList<>();
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Long> subscriptions = new ArrayList<>();
    @OneToOne(mappedBy = "follower")
    private User user;


    public List<Long> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Long> subscribers) {
        this.subscribers = subscribers;
    }

    public List<Long> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Long> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Follower{" +
                "subscribers=" + subscribers +
                ", subscriptions=" + subscriptions +
                '}';
    }
}
