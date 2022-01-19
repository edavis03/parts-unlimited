package mil.army.futures.asitemplate.controllers;

import mil.army.futures.asitemplate.Team;
import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TeamController {
    private final TeamRepository teamRepository;

    TeamController(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @GetMapping("/teams")
    public @ResponseBody
    List<String> getTeam() {
        return teamRepository.findAll().stream().map(Team::getName).collect(Collectors.toList());
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody String teamName) {
        var savedTeam = teamRepository.save(new Team(teamName));
        return ResponseEntity.ok().body(savedTeam.getName());
    }
}