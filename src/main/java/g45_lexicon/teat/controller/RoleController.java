package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.RoleDto;
import g45_lexicon.teat.service.RoleService;
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
@RequestMapping("/api/v1/role/")
 //http://localhost:8080/api/v1/role/
public class RoleController {
    @Autowired
    RoleService roleService;

    @Operation(summary = "Find all roles")
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

    @Operation(summary = "Find role by id")
    @GetMapping("id/{id}")
    public ResponseEntity<RoleDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(roleService.findById(id));
    }

    @Operation(summary = "Find role by title")
    @GetMapping("title/{title}")
    public ResponseEntity<RoleDto>findByTitle(@PathVariable("title") String title) throws DataNotFoundException {
        return ResponseEntity.ok().body(roleService.findByTitle(title));
    }

    @Operation(summary = "Create new role")
    @PostMapping
    public ResponseEntity<RoleDto> create(@RequestBody @Valid RoleDto roleDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("roleDto = " + roleDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleDto));
    }

    @Operation(summary = "Update role data")
    @PutMapping
    public ResponseEntity<RoleDto> update(@RequestBody @Valid RoleDto roleDto) throws DataNotFoundException, DataDuplicateException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(roleService.update(roleDto));
    }

    @Operation(summary = "Delete role by id")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
