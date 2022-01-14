package mil.army.futures.asitemplate.controllers;

import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public @ResponseBody
    List<String> getTeam() {
        return teamRepository.findAll();
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody String teamName) {
        return ResponseEntity.ok().body(teamRepository.save(teamName));
    }
}