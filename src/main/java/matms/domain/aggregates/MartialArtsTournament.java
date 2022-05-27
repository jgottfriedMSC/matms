package matms.domain.aggregates;

import java.util.Map;
import java.util.Map.Entry;

import matms.domain.MartialArt;
import matms.domain.entities.Participant;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Money;

public class MartialArtsTournament {

	private Id id;
	private String name;
	private Adress venue;
	private Money entryFee;
	private Money ticketPrice;
	private int numberOfTicketsSold;
	private Map<String, Participant> participants;
	private MartialArt martialArt;
	
	private MartialArtsTournament(final Id id, final String name, final Adress venue, final Money entryFee, final Money ticketPrice, final int numberOfTicketsSold, Map<String, Participant> participants, final MartialArt martialArt) {
		this.id = id;
		this.name = name;
		this.venue = venue;
		this.entryFee = entryFee;
		this.ticketPrice = ticketPrice;
		this.numberOfTicketsSold = numberOfTicketsSold;
		this.participants = participants;
		this.martialArt = martialArt;
	}
	
	public static MartialArtsTournamentBuilder builder() {
		return new MartialArtsTournamentBuilder();
	}
	
	public static class MartialArtsTournamentBuilder {
		
		private Id id;
		private String name;
		private Adress venue;
		private Money entryFee;
		private Money ticketPrice;
		private int numberOfTicketsSold;
		private Map<String, Participant> participants;
		private MartialArt martialArt;
		
		MartialArtsTournamentBuilder() {
			
		}
		
		public MartialArtsTournamentBuilder id(final Id id) {
			this.id = id;
			return this;
		}
		
		public MartialArtsTournamentBuilder name(final String name) {
			this.name = name;
			return this;
		}
		
		public MartialArtsTournamentBuilder venue(final Adress venue) {
			this.venue = venue;
			return this;
		}
		
		public MartialArtsTournamentBuilder entryFee(final Money entryFee) {
			this.entryFee = entryFee;
			return this;
		}
		
		public MartialArtsTournamentBuilder ticketPrice(final Money ticketPrice) {
			this.ticketPrice = ticketPrice;
			return this;
		}
		
		public MartialArtsTournamentBuilder numberOfTicketsSold(final int numberOfTicketsSold) {
			this.numberOfTicketsSold = numberOfTicketsSold;
			return this;
		}
		
		public MartialArtsTournamentBuilder participants(Map<String, Participant> participants) {
			this.participants = participants;
			return this;
		}
		
		public MartialArtsTournamentBuilder martialArt(final MartialArt martialArt) {
			this.martialArt = martialArt;
			return this;
		}
		
		public MartialArtsTournament build() {
			return new MartialArtsTournament(id, name, venue, entryFee, ticketPrice, numberOfTicketsSold, participants, martialArt);
		}
		
	}
	
	public Id getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public Adress getVenue() {
		return venue;
	}
	
	public Money getEntryFee() {
		return entryFee;
	}
	
	public Money getTicketPrice() {
		return ticketPrice;
	}
	
	public int getNumberOfTicketsSold() {
		return numberOfTicketsSold;
	}
	
	public Map<String, Participant> getParticipants() {
		return participants;
	}
	
	public MartialArt getMartialArt() {
		return martialArt;
	}
	
	@Override
	public String toString() {
		String participantString = "";
		for (Entry<String, Participant> p : participants.entrySet()) {
			if (participants.entrySet().size() == 1) {
				participantString += p.getValue().getId().getUuid();
			} else {
				participantString += p.getValue().getId().getUuid() + ",";
			}
			
		}
		
		return "MartialArtsTournament{" +
				"id='" + id.getUuid().toString() + "'" +
				", name='" + name + "'" +
				", venue='" + venue.toString() + "'" +
				", entryFee='" + entryFee + "'" +
				", ticketPrice='" + ticketPrice + "'" +
				", participants='" + participantString + "'" +
				", martialArt='" + martialArt + "'}";
				
	}
}
