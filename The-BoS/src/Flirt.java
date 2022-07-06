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
		
		ArrayList<Double> values = new ArrayList<>();
		
		values.add(payoffFaithCoy);
		values.add(payoffFaithFast);
		values.add(payoffPhilCoy);
		values.add(payoffPhilFast);
		
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
					if(random.nextBoolean())
						population.addPerson(new Man(true));
					else 
						population.addPerson(new Woman(true));
				
				boyfriend.setPartner(girlfriend);
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
			} else {
				for(int i = 0; i < nSons(boyfriend, girlfriend); i++)
					if(random.nextBoolean())
						population.addPerson(new Man(true));
					else
						population.addPerson(new Woman(false));
				
				boyfriend.setPartner(girlfriend);
				girlfriend.setPartner(boyfriend);
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
			}
		else
			if (!girlfriend.coy)
				for(int i = 0; i < nSons(boyfriend, girlfriend); i++)
					if(random.nextBoolean())
						population.addPerson(new Man(false));
					else
						population.addPerson(new Woman(false));
				
				population.addPerson(boyfriend);
				population.addPerson(girlfriend);
	}
}
