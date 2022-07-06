import java.util.ArrayList;
import java.util.Random;

public class Flirt extends Thread {
	
	Woman girlfriend;
	Man boyfriend;
	Population population;
	
	double payoffFaithCoy, payoffFaithFast, payoffPhilCoy, payoffPhilFast;
	
	Flirt(Man boyfriend, Woman girlfriend, Population population){
		this.boyfriend = boyfriend;
		this.girlfriend = girlfriend;
		this.population = population;
		
		payoffFaithCoy = population.a - (population.b/2) - population.c;
		payoffFaithFast = population.a - (population.b/2);
		payoffPhilCoy = population.a - population.b;
		payoffPhilFast = population.a;
		
	}

	synchronized int nSons(Man man, Woman woman) {
		Random random = new Random();
		
		double sons = 0;
		
		if(man.faithful)
			if (woman.coy)
				sons = payoffFaithCoy;
			else
				sons = payoffFaithFast;
		else
			sons = payoffPhilFast;
		
		sons = (population.maxSons * sons)/ payoffFaithCoy;
		int sonsInt = (int) sons;
		
		return sonsInt;
	}
	
	@Override
	public void run() {
		int a = population.a; int b = population.b; int c = population.c;
		
		Random random = new Random();
		
		if (boyfriend.faithful)
			if (girlfriend.coy) {
				for(int i = 0; i < nSons(boyfriend, girlfriend); i++)
					if(random.nextBoolean()) {
						population.addPerson(new Man(true));
						population.countFaith++;
					}
					else 
						population.addPerson(new Woman(true));
						population.countCoy++;
				
				boyfriend.setPartner(girlfriend);
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
			} else {
				for(int i = 0; i < nSons(boyfriend, girlfriend); i++)
					if(random.nextBoolean()) {
						population.addPerson(new Man(true));
						population.countFaith++;
					}
					else
						population.addPerson(new Woman(false));
						population.countFast++;
				
				boyfriend.setPartner(girlfriend);
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
			}
		else
			if (!girlfriend.coy)
				for(int i = 0; i < nSons(boyfriend, girlfriend); i++)
					if(random.nextBoolean()) {
						population.addPerson(new Man(false));
						population.countPhil++;
					}
					else
						population.addPerson(new Woman(false));
						population.countFast++;
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
	}
}
