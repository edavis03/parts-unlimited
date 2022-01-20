import nock from 'nock';
import {createTeam, getTeams} from "../teamsApiClient";

describe('teamsApiClient', () => {
  it('should make a GET request to get teams when getTeams is called', async () => {
    const expectedTeams = [{name: 'first-team-name'}, {name: 'second-team-name'}];
    nock('http://localhost').get('/teams').reply(200, expectedTeams);

    const teams = await getTeams();

    expect(teams).toEqual(expectedTeams);
  });

  it('should make a POST request to create a team when createTeam is called', async () => {
    const scope = nock('http://localhost', {
      reqheaders: {
        'Content-Type': 'text/plain'
      }
    }).post('/team', 'my-new-team')
      .reply(200, "my-new-team");

    const response = await createTeam("my-new-team");

    expect(scope.isDone()).toEqual(true);
    expect(response).toEqual("my-new-team");
  });

})