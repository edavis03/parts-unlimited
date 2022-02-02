import nock from 'nock';
import {createTeam, getTeams} from "../teamsApiClient";

describe('teamsApiClient', () => {
    describe('getTeams', () => {
        it('should make a GET request to retrieve all teams', async () => {
            const expectedTeams = [{name: 'first-team-name'}, {name: 'second-team-name'}];
            nock('http://localhost').get('/teams').reply(200, expectedTeams);

            const teams = await getTeams();

            expect(teams).toEqual(expectedTeams);
        });
    });

    describe('createTeam', () => {
        it('should make a POST request to create a team', async () => {
            const scope = nock('http://localhost', {
                reqheaders: {
                    'Content-Type': 'text/plain'
                }
            }).post('/teams', 'my-new-team')
                .reply(200, "my-new-team");

            const response = await createTeam("my-new-team");

            expect(scope.isDone()).toEqual(true);
            expect(response).toEqual("my-new-team");
        });
    });
});