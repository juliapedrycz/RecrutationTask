package recrutation.witek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.HttpClientErrorException;
import recrutation.witek.service.UserService;
import recrutation.witek.dto.UserQueryDto;


@RestController
@RequestMapping(method = RequestMethod.GET, value = "users/{login}")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<UserQueryDto> getByLogin(@PathVariable(value = "login") String login) {

        try {
            return ResponseEntity.ok(userService.getUser(login));
        } catch (HttpClientErrorException.NotFound ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
