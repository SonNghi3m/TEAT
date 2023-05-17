package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.ConversationDto;
import g45_lexicon.teat.service.ConversationService;
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

@RestController
@Validated
@RequestMapping("/api/v1/conversation/")
// http://localhost:8080/api/v1/conversation/
public class ConversationController {
    @Autowired
    ConversationService conversationService;
    @Operation(summary = "Find all conversations")
    @GetMapping
    public ResponseEntity<List<ConversationDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.getAll());
    }

    @Operation(summary = "Find conversation by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ConversationDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(conversationService.findById(id));
    }

    @Operation(summary = "Find conversation by chat name")
    @GetMapping("chatName/{chatName}")
    public ResponseEntity<List<ConversationDto>> findByChatName(@PathVariable("chatName") @NotEmpty @Size(max = 100) String chatName) {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.findByChatName(chatName));
    }

    @Operation(summary = "Create new conversation")
    @PostMapping
    public ResponseEntity<ConversationDto> create(@RequestBody @Valid ConversationDto conversationDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("conversationDto = " + conversationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(conversationService.create(conversationDto));
    }

    @Operation(summary = "Add a message to a conversation")
    @PostMapping("/{conversationId}/message/{messageId}")
    public ResponseEntity<ConversationDto> addMessage(
            @PathVariable("conversationId") Integer conversationId,
            @PathVariable("messageId") Integer messageId) throws DataNotFoundException, DataDuplicateException {
        return ResponseEntity.status(HttpStatus.CREATED).body(conversationService.addMessage(conversationId, messageId));
    }

    @Operation(summary = "Update conversation")
    @PutMapping
    public ResponseEntity<ConversationDto> update(@RequestBody @Valid ConversationDto conversationDto) throws DataNotFoundException, DataDuplicateException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(conversationService.update(conversationDto));
    }


    @Operation(summary = "Add a participant to a conversation")
    @PostMapping("/{conversationId}/participant/{participantId}")
    public ResponseEntity<ConversationDto> addParticipant(
            @PathVariable("conversationId") Integer conversationId,
            @PathVariable("participantId") Integer participantId) throws DataNotFoundException, DataDuplicateException {
        return ResponseEntity.status(HttpStatus.CREATED).body(conversationService.addParticipant(conversationId, participantId));
    }

    @Operation(summary = "Delete conversation by id")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
        conversationService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Remove a message from a conversation")
    @DeleteMapping("{conversationId}/message/{messageId}")
    public ResponseEntity<ConversationDto> removeMessage(
            @PathVariable("conversationId") Integer conversationId,
            @PathVariable("messageId") Integer messageId) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.removeMessage(conversationId, messageId));
    }
    @Operation(summary = "Remove a participant from a conversation")
    @DeleteMapping("{conversationId}/participant/{userId}")
    public ResponseEntity<ConversationDto> removeParticipant(
            @PathVariable("conversationId") Integer conversationId,
            @PathVariable("userId") Integer userId) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(conversationService.removeParticipant(conversationId, userId));
    }
}

