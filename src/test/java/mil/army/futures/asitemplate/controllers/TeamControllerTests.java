package mil.army.futures.asitemplate.controllers;


import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import mil.army.futures.asitemplate.repositories.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@WebMvcTest
@AutoConfigureMockMvc
public class TeamControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamRepository teamRepository;


    @Test
    public void whenCreatingTeams_delegatesToTeamRepository() throws Exception {
        when(teamRepository.save("first-team-name")).thenReturn("first-team-name");

        this.mockMvc.perform(post("/team").contentType(MediaType.TEXT_PLAIN).content("first-team-name"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("first-team-name")));

        verify(teamRepository, times(1)).save("first-team-name");
    }

    @Test
    public void whenGettingTeams_delegatesToTeamRepository() throws Exception {
        when(teamRepository.findAll()).thenReturn(List.of("first-team-name", "second-team-name"));

        this.mockMvc.perform(get("/teams")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("first-team-name")))
                .andExpect(content().string(containsString("second-team-name")));

        verify(teamRepository, times(1)).findAll();
    }
}