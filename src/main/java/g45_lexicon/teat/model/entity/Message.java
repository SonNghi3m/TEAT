package g45_lexicon.teat.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import g45_lexicon.teat.exception.DataNotFoundException;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Message {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id", updatable = false)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    private User sender;
    private String textContent;
    @ManyToMany
    @JoinTable(
            name = "messages_emojis",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "emoji_id")
    )
    private List<Emoji> emojis;
    @ManyToMany
    @JoinTable(
            name = "messages_attachments",
            joinColumns = @JoinColumn(name = "message_id"),
            inverseJoinColumns = @JoinColumn(name = "attachment_id")
    )
    private List<Attachment> attachments;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @Column(nullable = false)
    private boolean editedStatus;
    @Column(nullable = false)
    private boolean deletedStatus;
    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss a")
    private LocalDateTime timestamp;

    //constructors
    public Message(User sender, String textContent) {
        this.sender = sender;
        this.textContent = textContent;
    }

    public Message(Integer id, User sender, String textContent) {
        this.id = id;
        this.sender = sender;
        this.textContent = textContent;
    }

    public Message(Integer id, User sender, String textContent, List<Emoji> emojis, List<Attachment> attachments, Conversation conversation) {
        this.id = id;
        this.sender = sender;
        this.textContent = textContent;
        this.emojis = emojis;
        this.attachments = attachments;
        this.conversation = conversation;
    }

    //methods
    public void addEmoji(Emoji emoji) {
        if (emoji == null) throw new IllegalArgumentException("Emoji was null!");
        emojis.add(emoji);
    }

    public void removeEmoji(Emoji emoji) throws DataNotFoundException {
        if (emoji == null) throw new IllegalArgumentException("Emoji was null!");
        if (!emojis.contains(emoji)) throw new DataNotFoundException("Emoji does not exist!");
        emojis.remove(emoji);
    }

    public void addAttachment(Attachment attachment) {
        if (attachment == null) throw new IllegalArgumentException("Attachment was null!");
        attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) throws DataNotFoundException {
        if (attachment == null) throw new IllegalArgumentException("Attachment was null!");
        if (!attachments.contains(attachment)) throw new DataNotFoundException("Attachment does not exist!");
        attachments.remove(attachment);
    }
}
