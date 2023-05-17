package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EventDto;
import g45_lexicon.teat.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

//@RestController
//@Validated
//@RequestMapping("/api/v1/event/")
// http://localhost:8080/api/v1/event/
public class EventController {
//    @Autowired
//    EventService eventService;
//    @Operation(summary = "Find all events")
//    @GetMapping
//    public ResponseEntity<List<EventDto>> getAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(eventService.getAll());
//    }
//
//    @Operation(summary = "Find event by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<EventDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        return ResponseEntity.ok(eventService.findById(id));
//    }
//
//    @Operation(summary = "Find user by event name")
//    @GetMapping("/{name}")
//    public ResponseEntity<EventDto> findByName(@PathVariable("name") @NotEmpty @Size(min = 2, max = 100) String name) throws DataNotFoundException {
//        return ResponseEntity.ok().body(eventService.findByName(name));
//    }
//
//    //todo: find event by organizer/attendees
//
//    @Operation(summary = "Create new event")
//    @PostMapping
//    public ResponseEntity<EventDto> create(@RequestBody @Valid EventDto eventDto) throws DataDuplicateException, DataNotFoundException {
//        System.out.println("eventDto = " + eventDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(eventService.create(eventDto));
//    }
//
//    @Operation(summary = "Update event")
//    @PutMapping
//    public ResponseEntity<EventDto> update(@RequestBody @Valid EventDto eventDto) throws DataNotFoundException, DataDuplicateException {
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(eventService.update(eventDto));
//    }
//
//    @Operation(summary = "Delete event by id")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        eventService.delete(id);
//        return ResponseEntity.noContent().build();
//    }

}
