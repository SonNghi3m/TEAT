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
    @Column(name = "user_id", nullable = false, updatable = false)
    private Integer id;
    @Column(nullable = false, length = 40)
    private String firstName;
    @Column(nullable = false, length = 40)
    private String lastName;
    @Column(nullable = false, length = 100, unique = true)
    private String email;
    @Column(nullable = false, length = 50, unique = true)
    private String username;
    @Column(nullable = false, length = 20)
    private String password;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Message> messages;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "conversations_users",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id")
    )
    private List<Conversation> conversations;
//    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(
//            name = "events_attendees",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "event_id")
//    )
//    private List<Event> events;

    //constructors
    public User(String firstName, String lastName, String email, String username, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //    methods
//    public void addEvent(Event event) throws DataDuplicateException {
//        if (event == null) throw new IllegalArgumentException("Event was null!");
//        if (events.contains(event)) throw new DataDuplicateException("Event exist!");
//        events.add(event);
//    }
//
//    public void removeEvent(Event event) throws DataNotFoundException {
//        if (event == null) throw new IllegalArgumentException("Conversation was null!");
//        if (!events.contains(event)) throw new DataNotFoundException("Conversation does not exist!");
//        events.remove(event);
//    }
//    public void addConversation(Conversation conversation) throws DataDuplicateException {
//        if (conversation == null) throw new IllegalArgumentException("Conversation was null!");
//        if (conversations.contains(conversation)) throw new DataDuplicateException("Conversation exist!");
//        conversations.add(conversation);
//    }
//
//    public void removeConversation(Conversation conversation) throws DataNotFoundException {
//        if (conversation == null) throw new IllegalArgumentException("Conversation was null!");
//        if (conversations.contains(conversation)) throw new DataNotFoundException("Conversation does not exist!");
//        conversations.remove(conversation);
//    }


//    public void addMessage(Message message) throws DataDuplicateException {
//        if (message == null) throw new IllegalArgumentException("Message was null!");
//        if (messages.contains(message)) throw new DataDuplicateException("Message exist!");
//        messages.add(message);
//    }
//
//    public void removeMessage(Message message) throws DataNotFoundException {
//        if (message == null) throw new IllegalArgumentException("Message was null!");
//        if (!messages.contains(message)) throw new DataNotFoundException("Message does not exist!");
//        messages.remove(message);
//    }
}

