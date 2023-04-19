package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Conversation {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "conversation_id", updatable = false)
    private Integer id;
    @Column(nullable = false, length = 100)
    private String chatName;
    @ManyToMany
    @JoinTable(name = "conversations_users",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversation")
    private Set<Message> messages;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    //constructors
    public Conversation(String chatName, LocalDateTime timestamp) {
        this.chatName = chatName;
        this.timestamp = timestamp;
    }

    //methods
    public void addParticipant(User participant) {
        if (participant != null && !participants.contains(participant)) participants.add(participant);
    }

    public void removeParticipant(User participant) {
        if (participant != null && participants.contains(participant)) participants.remove(participant);
    }

    public void addMessage(Message msg) {
        if (msg != null && !messages.contains(msg)) messages.add(msg);
    }

    public void removeMessage(Message msg) {
        if (msg != null && messages.contains(msg)) messages.remove(msg);
    }
}
