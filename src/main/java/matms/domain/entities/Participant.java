package matms.domain.entities;

import java.util.ArrayList;
import java.util.List;

import matms.domain.MartialArt;
import matms.domain.valueobjects.Adress;
import matms.domain.valueobjects.Id;
import matms.domain.valueobjects.Weight;

public class Participant {

	private Id id;
	private String lastName;
	private String firstName;
	private Adress adress;
	private Weight weight;
	private boolean isLoser;
	private boolean payedFee;
	private boolean hasMatch;
	private List<MartialArt> martialArts;
	private List<Participant> playedAgainst = new ArrayList<>();
	
	private Participant(final Id id, final String lastName, final String firstName, final Adress adress, final Weight weight, boolean isLoser, boolean payedFee, final List<MartialArt> martialArts) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.adress = adress;
		this.weight = weight;
		this.isLoser = isLoser;
		this.payedFee = payedFee;
		this.martialArts = martialArts;
	}
	
	public static ParticipantBuilder builder() {
		return new ParticipantBuilder();
	}
	
	public static class ParticipantBuilder {
		
		private Id id;
		private String lastName;
		private String firstName;
		private Adress adress;
		private Weight weight;
		private boolean isLoser;
		private boolean payedFee;
		private List<MartialArt> martialArts;
		
		ParticipantBuilder() {
			
		}
		
		public ParticipantBuilder id(final Id id) {
			this.id = id;
			return this;
		}
		
		public ParticipantBuilder lastName(final String lastName) {
			this.lastName = lastName;
			return this;
		}
		
		public ParticipantBuilder firstName(final String firstName) {
			this.firstName = firstName;
			return this;
		}
		
		public ParticipantBuilder adress(final Adress adress) {
			this.adress = adress;
			return this;
		}
		
		public ParticipantBuilder weight(final Weight weight) {
			this.weight = weight;
			return this;
		}
		
		public ParticipantBuilder isLoser(boolean isLoser) {
			this.isLoser = isLoser;
			return this;
		}
		
		public ParticipantBuilder payedFee(boolean payedFee) {
			this.payedFee = payedFee;
			return this;
		}
		
		public ParticipantBuilder martialArts(final List<MartialArt> martialArts) {
			this.martialArts = martialArts;
			return this;
		}
		
		public Participant build() {
			return new Participant(id, lastName, firstName, adress, weight, isLoser, payedFee, martialArts);
		}
	}
	
	public Id getId() {
		return id;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public Adress getAdress() {
		return adress;
	}
	
	public Weight getWeight() {
		return weight;
	}
	
	public void addToPlayedAgainst(Participant participant) {
		playedAgainst.add(participant);
	}
	
	public List<Participant> getPlayedAgainst() {
		return playedAgainst;
	}
	
	public boolean isLoser() {
		return isLoser;
	}
	
	public void setMatch(boolean match) {
		hasMatch = match;
	}
	
	public boolean hasMatch() {
		return hasMatch;
	}
	
	public void lose() {
		this.isLoser = true;
	}
	
	public boolean payedFee() {
		return payedFee;
	}
	
	public void payFee() {
		this.payedFee = true;
	}
	
	public List<MartialArt> getMartialArts() {
		return martialArts;
	}
	
}
