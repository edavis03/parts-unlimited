package mil.army.futures.asitemplate.service;

import mil.army.futures.asitemplate.Team;
import mil.army.futures.asitemplate.services.TeamService;
import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TeamServiceTest {

    @MockBean
    TeamRepository teamRepository;

    TeamService teamService;

    @BeforeEach
    void setUp() {
        teamService = new TeamService(teamRepository);
    }

    @Test
    void shouldRetrieveAllTeams(){
        List<Team> expectedTeams = List.of(new Team(1L, "first-team"), new Team(2L, "second-team"));
        when(teamRepository.findAll()).thenReturn(expectedTeams);

        List<Team> actualTeams = teamService.getTeams();

        assertThat(actualTeams).isEqualTo(expectedTeams);
    }

    @Test
    void shouldCreateANewTeam(){
        Team teamToSave = new Team(1L, "first-team");

        teamService.createTeam(teamToSave);

        verify(teamRepository).save(teamToSave);
    }
}
