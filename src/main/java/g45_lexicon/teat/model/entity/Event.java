package g45_lexicon.teat.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
public class Event {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id", updatable = false)
    private Integer id;
    @Column(nullable = false)
    private String eventName;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDateTime startTime;
    @Column(nullable = false)
    private LocalDateTime endTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User organizer;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "events_attendees",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> attendees;

    //constructors
    public Event(String eventName, String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.eventName = eventName;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    //methods
    public void addAttendee(User attendee) {
        if (attendee != null && !attendees.contains(attendee)) attendees.add(attendee);
    }
    public void removeAttendee(User attendee) {
        if (attendee != null && attendees.contains(attendee)) attendees.remove(attendee);
    }
}
