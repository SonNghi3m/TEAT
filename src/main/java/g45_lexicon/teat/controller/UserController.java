package g45_lexicon.teat.controller;

import g45_lexicon.teat.exception.DataDuplicateException;
import g45_lexicon.teat.exception.DataNotFoundException;
import g45_lexicon.teat.model.dto.UserDto;
import g45_lexicon.teat.service.UserService;
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
@RequestMapping("/api/v1/user/")
// http://localhost:8080/api/v1/user/
public class UserController {
    @Autowired
    UserService userService;
    @Operation(summary = "Find all users")
    @GetMapping
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @Operation(summary = "Find user by id")
    @GetMapping("id/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Integer id) throws DataNotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @Operation(summary = "Find user by username")
    @GetMapping("username/{username}")
    public ResponseEntity<UserDto> findByUsername(@PathVariable("username") @NotEmpty @Size(min = 4, max = 50) String username) throws DataNotFoundException {
        return ResponseEntity.ok().body(userService.findByUsername(username));
    }

    @Operation(summary = "Find user by email")
    @GetMapping("email/{email}")
    public ResponseEntity<UserDto> findByEmail(@PathVariable("email") String email) throws DataNotFoundException {
        return ResponseEntity.ok().body(userService.findByEmail(email));
    }
    @Operation(summary = "Register new user")
    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserDto userDto) throws DataDuplicateException, DataNotFoundException {
        System.out.println("userDto = " + userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userDto));
    }

    @Operation(summary = "Update user data")
    @PutMapping
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto) throws DataNotFoundException, DataDuplicateException {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userService.update(userDto));
    }

    @Operation(summary = "Delete user by id")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Integer id) throws DataNotFoundException {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete user by username")
    @DeleteMapping("username/{username}")
    public ResponseEntity<Void> deleteByUsername(@PathVariable("username") String username) throws DataNotFoundException {
        userService.deleteByUsername(username);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Delete user by email")
    @DeleteMapping("email/{email}")
    public ResponseEntity<Void> deleteByEmail(@PathVariable("email") String email) throws DataNotFoundException {
        userService.deleteByEmail(email);
        return ResponseEntity.noContent().build();
    }
}
