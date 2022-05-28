package matms.plugins.tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.FileNotFoundException;

import org.mockito.Mockito;

import matms.adapters.RoundService;
import matms.domain.exceptions.NoWinnerException;
import matms.domain.exceptions.TournamentNotFoundException;
import matms.domain.repositories.MartialArtsTournamentRepository;
import matms.domain.repositories.UserRepository;
import matms.plugins.TextStatistics;

public class TextStatisticsTest {
	
	@Test
	public void constructorTest() {
		RoundService roundService = Mockito.mock(RoundService.class);
		UserRepository userRepository = Mockito.mock(UserRepository.class);
		MartialArtsTournamentRepository tournamentRepository = Mockito.mock(MartialArtsTournamentRepository.class);
		
		TextStatistics stats = new TextStatistics(roundService, userRepository, tournamentRepository);
		assertFalse(stats.equals(null));
	}
	
	@Test
	public void createDataOutputTest() throws FileNotFoundException, NoWinnerException, TournamentNotFoundException {		
		
	}
	
}
