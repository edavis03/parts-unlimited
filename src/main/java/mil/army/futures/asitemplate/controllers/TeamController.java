package mil.army.futures.asitemplate.controllers;

import mil.army.futures.asitemplate.Team;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TeamController {
    private final TeamService teamService;

    TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public @ResponseBody
    List<String> getTeams() {
        List<String> teamNames = new ArrayList<>();
        for(Team team: teamService.getTeams()){
            teamNames.add(team.getName());
        }
        return teamNames;
    }

    @PostMapping("/team")
    public ResponseEntity<String> createTeam(@RequestBody String teamName) {
        var savedTeam = teamService.createTeam(new Team(teamName));
        URI location = createResourceLocation("/teams",savedTeam.getId());
        return ResponseEntity.created(location).body(savedTeam.getName());
    }

    private URI createResourceLocation(String path, Long resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().port("8080").path(path)
                .buildAndExpand(resourceId).toUri();
    }
}