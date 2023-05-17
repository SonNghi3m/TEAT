package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.EmojiDto;
import g45_lexicon.teat.service.EmojiService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//@RestController
//@Validated
//@RequestMapping("/api/v1/emoji/")
// http://localhost:8080/api/v1/emoji/
public class EmojiController {
//    @Autowired
//    EmojiService emojiService;
//    @Operation(summary = "Find all emojis")
//    @GetMapping
//    public ResponseEntity<List<EmojiDto>> getAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(emojiService.getAll());
//    }
//
//    @Operation(summary = "Find emoji by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<EmojiDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        return ResponseEntity.ok(emojiService.findById(id));
//    }
//
//    @Operation(summary = "Find emoji by file name")
//    @GetMapping("/{name}")
//    public ResponseEntity<EmojiDto>findByName(@PathVariable("name") String name) throws DataNotFoundException {
//        return ResponseEntity.ok().body(emojiService.findByName(name));
//    }
//
//    @Operation(summary = "Create new emoji")
//    @PostMapping
//    public ResponseEntity<EmojiDto> create(@RequestBody @Valid EmojiDto emojiDto) throws DataDuplicateException, DataNotFoundException {
//        System.out.println("emojiDto = " + emojiDto);
//        return ResponseEntity.status(HttpStatus.CREATED).body(emojiService.create(emojiDto));
//    }
//
//    @Operation(summary = "Update emoji")
//    @PutMapping
//    public ResponseEntity<EmojiDto> update(@RequestBody @Valid EmojiDto emojiDto) throws DataNotFoundException, DataDuplicateException {
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(emojiService.update(emojiDto));
//    }
//
//    @Operation(summary = "Delete emoji by id")
//    @DeleteMapping
//    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        emojiService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
