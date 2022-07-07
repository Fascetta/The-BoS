import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	final int a,b,c;
	int deathAge; 
	int countFast, countCoy, countPhil, countFaith;
	int pointFast, pointCoy, pointPhil, pointFaith;
	int nMen, nWomen;
	
	ArrayList<Man> men = new ArrayList<>();
	ArrayList<Woman> women = new ArrayList<>();
	
	Population(int starterP, int a, int b, int c, int deathAge){

		this.a = a;
		this.b = b;
		this.c = c;
		this.deathAge = deathAge;
		
		this.countCoy = starterP;
		this.countFaith = starterP;
		this.countFast = starterP;
		this.countPhil = starterP;
		
		for(int i = 0; i < starterP; i++) {
			men.add(new Man(true));
			men.add(new Man(false));
			women.add(new Woman(true));
			women.add(new Woman(false));
			// System.out.println(men.size());
		}
	}
	
	void nextGeneration(){
		for(int i = 0; i < pointFaith / 10 ; i++) {
			this.addPerson(new Man(true));
		}
		
		for(int i = 0; i < pointPhil / 10; i++) {
			this.addPerson(new Man(false));
		}
		
		for(int i = 0; i < pointCoy / 10; i++) {
			this.addPerson(new Woman(true));
		}
		
		for(int i = 0; i < pointFast / 10; i++) {
			this.addPerson(new Woman(false));
		}
		
		pointFaith = pointPhil = pointCoy = pointFast = 0;
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
	
	synchronized Woman pickGirl() throws NoSuchSexException{
		if (women.size() == 0) throw new NoSuchSexException("Women");
		
		Random random = new Random();
		return women.get(random.nextInt(women.size()));
	}
	
	synchronized void growing() {
		
		int deaths = 0;
		
		for (int i = 0; i < men.size(); i++) {
			if (men.get(i).age >= deathAge) {
				removePerson(men.get(i));
				deaths++;
				i--;
			}
		}
		
		for (int i = 0; i < women.size(); i++) {
			if (women.get(i).age >= deathAge) {
				removePerson(women.get(i));
				deaths++;
				i--;
			}
		}
		
		// System.out.println("This year " + deaths + " died.");
	}
	
	
	void info() {
		System.out.println("There are " + men.size() + " men, " + countFaith + " are Faithful, " + countPhil + " are Philanthropist.");
		System.out.println("There are " + women.size() + " women, " + countCoy + " are Coy, " + countFast + " are Fast.");
		System.out.println("Faithful are the " + ((countFaith * 100) /men.size()) + "% of the men.");
		System.out.println("Coy are the " + ((countCoy * 100) /women.size()) + "% of the women.");
		System.out.println();

	}
	
	
}
