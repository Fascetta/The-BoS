import java.util.ArrayList;
import java.util.Random;

public class Flirt extends Thread {
	
	Woman girlfriend;
	Man boyfriend;
	Population population;
	
	double payoffFaithCoy, payoffFaithFast, payoffPhil, payoffFast;
	
	Flirt(Man boyfriend, Woman girlfriend, Population population){
		this.boyfriend = boyfriend;
		this.girlfriend = girlfriend;
		this.population = population;
		
		payoffFaithCoy = population.a - (population.b/2.0) - population.c;
		payoffFaithFast = population.a - (population.b/2.0);
		payoffPhil = population.a - population.b;
		payoffFast = population.a;
		
	}

	synchronized int nSons(Man man, Woman woman, boolean dad) {
		Random random = new Random();
		
		ArrayList<Double> values = new ArrayList<>();
		
		values.add(payoffFaithCoy);
		values.add(payoffFaithFast);
		values.add(payoffFast);
		values.add(payoffPhil);
		
		double maxE = payoffFaithCoy;
		
		for(Double i : values)
			if(i > maxE)
				maxE = i;
		
		double sons = 0;
		
		if(man.faithful)
			if (woman.coy)
				sons = payoffFaithCoy;
			else
				sons = payoffFaithFast;
		else
			if(dad)
				sons = payoffPhil;
			else
				sons = payoffFast;
		
		sons = (population.maxSons * sons)/ payoffFaithCoy;
		int sonsInt = (int) sons;
		return sonsInt;
	}
	
	@Override
	public void run() {
		int a = population.a; int b = population.b; int c = population.c;
		Random random = new Random();
		
		int chance = random.nextInt(100); 
		
		if (boyfriend.faithful)
			if (girlfriend.coy) { // Faith and Coy
				if(chance <= 60)
					if(random.nextBoolean()) { // God wish
						population.addPerson(new Man(true)); // A Faithful is born
					}
					else 
						population.addPerson(new Woman(true)); // A Coy is born
				
				boyfriend.setPartner(girlfriend); // they are now a couple
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend); // they return in the array
				population.addPerson(girlfriend);
					
			} else { // Faith and Fast
				if(random.nextBoolean()) 
					if(chance <= 60)
						population.addPerson(new Man(true)); // a Faith
				else
					if(chance > 60)
						population.addPerson(new Woman(false)); // a Fast
				
				boyfriend.setPartner(girlfriend);
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
			}
		else
			if (!girlfriend.coy)
				if(chance > 145)
					if(random.nextBoolean()) {
						population.addPerson(new Man(false));
					}
					else
						population.addPerson(new Woman(false));
		
			population.addPerson(boyfriend);
			population.addPerson(girlfriend);
	}
}
