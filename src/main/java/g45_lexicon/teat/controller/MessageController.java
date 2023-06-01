package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.MessageDto;
import g45_lexicon.teat.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/message/")
 //http://localhost:8080/api/v1/message/
public class MessageController {
    @Autowired
    MessageService messageService;
//    private final MessageService messageService;
//    private final SimpMessagingTemplate messagingTemplate;
//
//    @Autowired
//    public MessageController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
//        this.messageService = messageService;
//        this.messagingTemplate = messagingTemplate;
//    }
    @Operation(summary = "Find all messages")
    @GetMapping
    public ResponseEntity<List<MessageDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.getAll());
    }

    @Operation(summary = "Find message by id")
    @GetMapping("id/{id}")
    public ResponseEntity<MessageDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findById(id));
    }

    @Operation(summary = "Find message by sender")
    @GetMapping("sender/{username}")
    public ResponseEntity<List<MessageDto>> findBySender(@PathVariable("username") String username) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(messageService.findBySender(username));
    }

    @Operation(summary = "Create new message")
    @PostMapping
    public ResponseEntity<MessageDto> create(@RequestBody @Valid MessageDto messageDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("messageDto = " + messageDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageService.create(messageDto));
    }

//    @Operation(summary = "Create new message")
//    @PostMapping
//    public ResponseEntity<MessageDto> create(@RequestBody @Valid MessageDto messageDto) throws DataDuplicateException, DataNotFoundException {
//        MessageDto createdMessage = messageService.create(messageDto);
//
//        // Send the created message to subscribers
//        messagingTemplate.convertAndSend("/topic/messages", createdMessage);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdMessage);
//    }

    @Operation(summary = "Update message")
    @PutMapping
    public ResponseEntity<MessageDto> update(@RequestBody @Valid MessageDto messageDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(messageService.update(messageDto));
    }

    @Operation(summary = "Delete message by id")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
        messageService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete all messages")
    @DeleteMapping
    public ResponseEntity<Void> deleteAll() {
        messageService.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
