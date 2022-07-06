import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	int a,b,c, deathAge, maxSons, countFast, countCoy, countPhil, countFaith, nMen, nWomen;
	
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
		if(man.faithful)
			countFaith++;
		else
			countPhil++;
	}
	
	synchronized void addPerson(Woman woman) {
		women.add(woman);
		if(woman.coy)
			countCoy++;
		else
			countFast++;
	}
	
	synchronized void removePerson(Man man) {
		men.remove(man);
		if(man.faithful)
			countFaith--;
		else
			countPhil--;
	}
	
	synchronized void removePerson(Woman woman) {
		women.remove(woman);
		if(woman.coy)
			countCoy--;
		else
			countFast--;
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
		int nMen = (countFaith + countPhil);
		int nWomen = (countCoy + countFast);
		System.out.println("There are " + nMen + " men, " + countFaith + " are Faithful, " + countPhil + " are Philanthropist.");
		System.out.println("There are " + nWomen + " women, " + countCoy + " are Coy, " + countFast + " are Fast.");
		System.out.println("Faithful are the " + ((countFaith * 100) /nMen) + "% of the men.");
		System.out.println("Coy are the " + ((countCoy * 100) /nWomen) + "% of the women.");
		System.out.println("Total inhabitans: " + (nMen + nWomen));
		System.out.println();
	}
	
	
}
