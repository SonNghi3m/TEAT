package g45_lexicon.teat.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "conversations_users",
            joinColumns = @JoinColumn(name = "conversation_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants;
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Message> messages;
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime timestamp;

    //constructors


    public Conversation(String chatName) {
        this.chatName = chatName;
    }

//    methods
    public void addParticipant(User participant) throws DataDuplicateException {
        if (participant == null) throw new IllegalArgumentException("Participant was null!");
        if (participants.contains(participant)) throw new DataDuplicateException("Participant exist!");
        participants.add(participant);
    }

    public void removeParticipant(User participant) throws DataNotFoundException {
        if (participant == null) throw new IllegalArgumentException("Participant was null!");
        if (!participants.contains(participant)) throw new DataNotFoundException("Participant does not exist!");
        participants.remove(participant);
    }

    public void addMessage(Message message) throws DataDuplicateException {
        if (message == null) throw new IllegalArgumentException("Message was null!");
        if (messages.contains(message)) throw new DataDuplicateException("Message exist!");
        messages.add(message);
        message.setConversation(this);
    }

    public void removeMessage(Message message) throws DataNotFoundException {
        if (message == null) throw new IllegalArgumentException("Message was null!");
        if (!messages.contains(message)) throw new DataNotFoundException("Message does not exist!");
        messages.remove(message);
    }
}
