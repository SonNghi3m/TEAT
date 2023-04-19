package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

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
    @ManyToOne(cascade = CascadeType.ALL)
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;
    @Column(nullable = false)
    private boolean editedStatus;
    @Column(nullable = false)
    private boolean deletedStatus;
    @Column(nullable = false)
    private LocalDateTime timestamp;

    //constructors
    public Message(String textContent, boolean editedStatus, boolean deletedStatus, LocalDateTime timestamp) {
        this.textContent = textContent;
        this.editedStatus = editedStatus;
        this.deletedStatus = deletedStatus;
        this.timestamp = timestamp;
    }

    //methods
    public void addEmoji(Emoji emoji) {
        if (emoji != null && !emojis.contains(emoji)) emojis.add(emoji);
    }

    public void removeEmoji(Emoji emoji) {
        if (emoji != null && emojis.contains(emoji)) emojis.remove(emoji);
    }

    public void addAttachment(Attachment attachment) {
        if (attachment != null && !attachments.contains(attachment)) attachments.add(attachment);
    }

    public void removeAttachment(Attachment attachment) {
        if (attachment != null && attachments.contains(attachment)) attachments.remove(attachment);
    }
}
