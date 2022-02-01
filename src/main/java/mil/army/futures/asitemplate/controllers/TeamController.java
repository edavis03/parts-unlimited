package mil.army.futures.asitemplate.controllers;

import mil.army.futures.asitemplate.Team;
import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    List<String> getTeams() {
        return teamRepository.findAll().stream().map(Team::getName).collect(Collectors.toList());
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody String teamName) {
        var savedTeam = teamRepository.save(new Team(teamName));
        URI location = createResourceLocation("/teams",savedTeam.getId());
        return ResponseEntity.created(location).body(savedTeam.getName());
    }

    private URI createResourceLocation(String path, Long resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().port("8080").path(path)
                .buildAndExpand(resourceId).toUri();
    }
}