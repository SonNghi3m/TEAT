package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
public class Emoji {
    //fields
    @Id
    @Column(name = "emoji_id", updatable = false)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String emojiName;
    @Column(nullable = false)
    private String emojiPath;
    @ManyToMany
    @JoinTable(
            name = "messages_emojis",
            joinColumns = @JoinColumn(name = "emoji_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messages;

    //constructors
    public Emoji(String emojiName, String emojiPath) {
        this.emojiName = emojiName;
        this.emojiPath = emojiPath;
    }

    //methods
    public void addMessage(Message msg) {
        if (msg != null && !messages.contains(msg)) messages.add(msg);
    }

    public void removeMessage(Message msg) {
        if (msg != null && messages.contains(msg)) messages.remove(msg);
    }
}
