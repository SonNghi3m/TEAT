package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Attachment {
    //fields
    @Id
    @Column(name = "attachment_id", updatable = false)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String fileName;
    @Column(nullable = false, unique = true)
    private String filePath;
    @ManyToMany
    @JoinTable(
            name = "messages_attachments",
            joinColumns = @JoinColumn(name = "attachment_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messages;

    //constructors
    public Attachment(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    //methods
    public void addMessage(Message msg) {
        if (msg != null && !messages.contains(msg)) messages.add(msg);
    }

    public void removeMessage(Message msg) {
        if (msg != null && messages.contains(msg)) messages.remove(msg);
    }
}
