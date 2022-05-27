package matms.domain.entities.tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.exceptions.InvalidWeightException;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class ParticipantSpec {

	private static Participant p;
	private static Id id;
	private static Adress adress;
	private static Weight weight;
	private static List<MartialArt> martialArts;
	
	@BeforeAll
	public static void initializeParticipant() {
		id = Mockito.mock(Id.class);
		adress = Mockito.mock(Adress.class);
		weight = Mockito.mock(Weight.class);
		martialArts = Mockito.mock(List.class);
		
		p = Participant.builder()
							.id(id)
							.firstName("a")
							.lastName("b")
							.adress(adress)
							.weight(weight)
							.isLoser(false)
							.payedFee(false)
							.martialArts(martialArts)
							.build();
	}
	
	@Test
	public void builderTest() {
		assertTrue(p.getFirstName().equals("a"));
		assertTrue(p.getLastName().equals("b"));
		assertTrue(p.getId().equals(id));
		assertTrue(p.getAdress().equals(adress));
		assertTrue(p.getWeight().equals(weight));
		assertTrue(p.getMartialArts().equals(martialArts));
	}
	
	@Test
	public void addToPlayedAgainstTest() {
		Participant p1 = Mockito.mock(Participant.class);
		p.addToPlayedAgainst(p1);
		assertTrue(p.getPlayedAgainst().size() == 1);
	}
	
	@Test
	public void loserTest() {
		p.lose();
		assertTrue(p.isLoser());
	}
	
	@Test
	public void matchTest() {
		p.setMatch(true);
		assertTrue(p.hasMatch());
	}
	
	@Test
	public void payFeeTest() {
		p.payFee();
		assertTrue(p.payedFee());
	}
	
	@Test
	public void toStringWithOneArtTest() throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		Participant p = Participant.builder()
				.id(id)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		String participantString = "Participant{" +
				"id='" + id.getUuid().toString() + "'" +
				", firstName='a'" +
				", lastName='b'" +
				", adress='" + adress.toString() + "'" +
				", weight='" + weight.toString() + "'" +
				", martialArts='" + arts.get(0).toString() + "'}";
		assertTrue(p.toString().equals(participantString));
	}
	
	@Test
	public void toStringWithMoreArtsTest() throws InvalidWeightException {
		Id id = new Id(UUID.randomUUID().toString());
		Adress adress = new Adress("a", "b", 1, 1);
		Weight weight = new Weight(1, "KG");
		List<MartialArt> arts = new ArrayList<>();
		arts.add(MartialArt.BOX);
		arts.add(MartialArt.KARATE);
		Participant p = Participant.builder()
				.id(id)
				.firstName("a")
				.lastName("b")
				.adress(adress)
				.weight(weight)
				.isLoser(false)
				.payedFee(false)
				.martialArts(arts)
				.build();
		String participantString = "Participant{" +
				"id='" + id.getUuid().toString() + "'" +
				", firstName='a'" +
				", lastName='b'" +
				", adress='" + adress.toString() + "'" +
				", weight='" + weight.toString() + "'" +
				", martialArts='" + arts.get(0).toString() + "," + arts.get(1).toString() + ",'}";
		assertTrue(p.toString().equals(participantString));
	}
	
}
