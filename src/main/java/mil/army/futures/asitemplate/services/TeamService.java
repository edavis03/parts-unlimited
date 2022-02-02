package mil.army.futures.asitemplate.services;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.Team;
import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeamService {

    TeamRepository teamRepository;

    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public List<Team> getTeams() {
        return teamRepository.findAll();
    }
}
