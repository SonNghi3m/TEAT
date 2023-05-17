package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.PermissionDto;
import g45_lexicon.teat.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/v1/permission/")
// http://localhost:8080/api/v1/permission/
public class PermissionController {
    @Autowired
    PermissionService permissionService;
    @Operation(summary = "Find all permissions")
    @GetMapping
    public ResponseEntity<List<PermissionDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(permissionService.getAll());
    }

    @Operation(summary = "Find permission by id")
    @GetMapping("id/{id}")
    public ResponseEntity<PermissionDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(permissionService.findById(id));
    }

    @Operation(summary = "Find role by permission name")
    @GetMapping("name/{name}")
    public ResponseEntity<PermissionDto>findByName(@PathVariable("name") String name) throws DataNotFoundException {
        return ResponseEntity.ok().body(permissionService.findByName(name));
    }

    @Operation(summary = "Create new permission")
    @PostMapping
    public ResponseEntity<PermissionDto> create(@RequestBody @Valid PermissionDto permissionDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("permissionDto = " + permissionDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(permissionService.create(permissionDto));
    }

    @Operation(summary = "Update permission data")
    @PutMapping
    public ResponseEntity<PermissionDto> update(@RequestBody @Valid PermissionDto permissionDto) throws DataNotFoundException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(permissionService.update(permissionDto));
    }

    @Operation(summary = "Delete permission by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
