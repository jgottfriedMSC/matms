package matms.adapter.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import matms.adapters.RoundService;
import matms.domain.Round;
import matms.domain.TournamentMode;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;

public class RoundServiceTest {

	private RoundService roundService;

	@Test
	public void constructorTest() throws TournamentNotFoundException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		assertFalse(roundService.equals(null));
	}
	
	@Test
	public void playRoundTest() throws TournamentNotFoundException {
		Round round = Mockito.mock(Round.class);
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.playRound(round);
		Mockito.verify(tournamentMode, Mockito.times(1)).playRound(round);		
	}
	
	@Test
	public void nextRoundTest() throws TournamentNotFoundException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.nextRound();
		Mockito.verify(tournamentMode, Mockito.times(1)).nextRound();	
	}
	
	@Test
	public void getTournamentWinnerTest() throws TournamentNotFoundException, NoWinnerException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.getTournamentWinner();
		Mockito.verify(tournamentMode, Mockito.times(1)).getWinner();
	}
	
	@Test
	public void getCurrentRoundTest() throws TournamentNotFoundException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.getCurrentRound();
		Mockito.verify(tournamentMode, Mockito.times(1)).getCurrentRound();
	}
	
	@Test
	public void checkIfThereIsWinnerTest() throws TournamentNotFoundException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.checkIfThereIsWinner();
		Mockito.verify(tournamentMode, Mockito.times(1)).checkIfThereIsWinner();
	}
	
	@Test
	public void getParticipantPointsTest() throws TournamentNotFoundException {
		TournamentMode tournamentMode = Mockito.mock(TournamentMode.class);
		
		roundService = new RoundService(tournamentMode);
		roundService.getParticipantPoints();
		Mockito.verify(tournamentMode, Mockito.times(1)).getParticipantPoints();
	}
	
}
