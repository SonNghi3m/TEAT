package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id",nullable = false, updatable = false)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false,length = 20)
    private String password;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToMany
    @JoinTable(name = "conversations_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<Conversation> conversations;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "events_attendees",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> events;

    //constructors
    public User(String firstName, String lastName, String email, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //methods
    public void addConversation(Conversation conversation) {
        if (conversation != null && !conversations.contains(conversation)) conversations.add(conversation);
    }

    public void removeConversation(Conversation conversation) {
        if (conversation != null && conversations.contains(conversation)) conversations.remove(conversation);
    }

    public void addEvent(Event event) {
        if (event != null && !events.contains(event)) events.add(event);
    }
    public void removeEvent(Event event) {
        if (event != null && events.contains(event)) events.remove(event);
    }

}

