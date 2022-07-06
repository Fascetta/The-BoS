import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	int a,b,c, deathAge, maxSons, countFast, countCoy, countPhil, countFaith;
	
	ArrayList<Man> men = new ArrayList<>();
	ArrayList<Woman> women = new ArrayList<>();
	
	Population(int starterP, int a, int b, int c, int deathAge, int maxSons){

		this.a = a;
		this.b = b;
		this.c = c;
		this.deathAge = deathAge;
		this.maxSons = maxSons;
		
		this.countCoy = starterP;
		this.countFaith = starterP;
		this.countFast = starterP;
		this.countPhil = starterP;
		
		for(int i = 0; i < starterP; i++) {
			men.add(new Man(true));
			men.add(new Man(false));
			women.add(new Woman(true));
			women.add(new Woman(false));
			
		}
	}
	
	synchronized void addPerson(Man man) {
		men.add(man);
	}
	
	synchronized void addPerson(Woman woman) {
		women.add(woman);
	}
	
	synchronized void removePerson(Man man) {
		men.remove(man);
	}
	
	synchronized void removePerson(Woman woman) {
		women.remove(woman);
	}

	synchronized Man pickBoy() throws NoSuchSexException{
		if (men.size() == 0) throw new NoSuchSexException("Men");
		
		Random random = new Random();
		return men.remove(random.nextInt(men.size()));
	}
	
	synchronized Woman pickGirl() throws NoSuchSexException{
		if (women.size() == 0) throw new NoSuchSexException("Women");
		
		Random random = new Random();
		return women.remove(random.nextInt(women.size()));
	}
	
	synchronized void growing() {
		
		int deaths = 0;
		
		for (int i = 0; i < men.size(); i++) {
			if (men.get(i).age >= deathAge) {
				if(men.get(i).partner != null)
					men.get(i).partner.setPartner(null);
				
			removePerson(men.get(i));
			deaths++;
			i--;
			}
		}
		
		for (int i = 0; i < women.size(); i++) {
			if (women.get(i).age >= deathAge) {
				if(women.get(i).partner != null)
					women.get(i).partner.setPartner(null);
				
			removePerson(women.get(i));
			deaths++;
			i--;
			}
		}
		
		System.out.println("This year " + deaths + " died.");
	}
	
	
	void info() {
		System.out.println("There are " + men.size() + " men, " + countFaith + " are Faithful, " + countPhil + " are Philanthropist.");
		System.out.println("There are " + women.size() + " women, " + countCoy + " are Coy, " + countFast + " are Fast.");
		System.out.println("Faithful are the " + ((countFaith * 100) /men.size()) + "% of the men.");
		System.out.println("Coy are the " + ((countCoy * 100) /women.size()) + "% of the women.");
		System.out.println("Total inhabitans: " + (men.size() + women.size()));
		System.out.println();
	}
	
	
}
