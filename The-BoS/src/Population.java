import java.util.ArrayList;
import java.util.Random;

public class Population {
	
	final int a,b,c;
	int deathAge; 
	int countFast, countCoy, countPhil, countFaith;
	int pointFast, pointCoy, pointPhil, pointFaith;
	
	ArrayList<Man> men = new ArrayList<>();
	ArrayList<Woman> women = new ArrayList<>();
	
	Population(int starterP, int a, int b, int c, int deathAge){

		this.a = a;
		this.b = b;
		this.c = c;
		this.deathAge = deathAge;
		
		for(int i = 0; i < starterP; i++) {
			this.addMan(true);
			this.addMan(false);
			this.addWoman(true);
			this.addWoman(false);
		}
	}
	
	void nextGeneration(){
		

		for(int f = 0; f < pointFaith; f++) {
			this.addMan(true);
		}
		
		for(int p = 0; p < pointPhil; p++) {
			this.addMan(false);
		}
		
		for(int c = 0; c < pointCoy; c++) {
			this.addWoman(true);
		}
		
		for(int s = 0; s < pointFast; s++) {
			this.addWoman(false);
		}
	pointFaith = pointPhil = pointCoy = pointFast = 0;
	}
	
	synchronized void addMan(boolean faith) {
		men.add(new Man(faith));
		if(faith) 
			countFaith++;
		else
			countPhil++;
	}
	
	synchronized void addWoman(boolean coy) {
		women.add(new Woman(coy));
		if(coy) 
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
		
		System.out.println("This year " + deaths + " died.");
	}


	void info() throws ReachedStabilityException, TypeHasDiedException {
		int faithPercentage = (countFaith * 100) / men.size(); //ideally 62
		int coyPercentage = (countCoy * 100) / women.size(); //ideally 83

		System.out.println(men.size() + " men, " + countFaith + " are Faithful, " + countPhil + " are Philanderers.");
		System.out.println(women.size() + " women, " + countCoy + " are Coy, " + countFast + " are Fast.");
		System.out.println("Faithful are the " + faithPercentage + "% of the men.");
		System.out.println("Coy are the " + coyPercentage + "% of the women.");
		System.out.println();

		if ((faithPercentage >= 57 && faithPercentage <= 68) && (coyPercentage >= 77 && coyPercentage <= 88)){
			throw new ReachedStabilityException(faithPercentage, coyPercentage);
		}

		if (countCoy == 0 || countFaith == 0 || countFast == 0 || countPhil == 0){
			throw new TypeHasDiedException();
		}
	}
}
