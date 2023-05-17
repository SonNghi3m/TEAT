package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.AttachmentDto;
import g45_lexicon.teat.model.entity.Attachment;
import g45_lexicon.teat.service.AttachmentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/attachment/")
// http://localhost:8080/api/v1/attachment/
public class AttachmentController {
    @Autowired
    AttachmentService attachmentService;
    @Operation(summary = "Find all attachments")
    @GetMapping
    public ResponseEntity<List<AttachmentDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(attachmentService.getAll());
    }
//
//    @Operation(summary = "Find attachment by id")
//    @GetMapping("/{id}")
//    public ResponseEntity<AttachmentDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        return ResponseEntity.ok(attachmentService.findById(id));
//    }
//
//    @Operation(summary = "Find attachment by file name")
//    @GetMapping("/{name}")
//    public ResponseEntity<AttachmentDto>findByName(@PathVariable("name") String name) throws DataNotFoundException {
//        return ResponseEntity.ok().body(attachmentService.findByName(name));
//    }
//
    @Operation(summary = "Create new attachment")
    @PostMapping
    public ResponseEntity<AttachmentDto> create(@RequestBody @Valid AttachmentDto attachmentDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("attachmentDto = " + attachmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(attachmentService.create(attachmentDto));
    }
//
//    @Operation(summary = "Update attachment")
//    @PutMapping
//    public ResponseEntity<AttachmentDto> update(@RequestBody @Valid AttachmentDto attachmentDto) throws DataNotFoundException, DataDuplicateException {
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(attachmentService.update(attachmentDto));
//    }
//
//    @Operation(summary = "Delete attachment by id")
//    @DeleteMapping
//    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
//        attachmentService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
