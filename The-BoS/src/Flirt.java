public class Flirt extends Thread {
	
	Woman girlfriend;
	Man boyfriend;
	Population population;
	
	double payoffFaithCoy, payoffFaithFast, payoffPhil, payoffFast;
	
	Flirt(Man boyfriend, Woman girlfriend, Population population){
		this.boyfriend = boyfriend;
		this.girlfriend = girlfriend;
		this.population = population;
		
		payoffFaithCoy = population.a - (population.b/2) - population.c;
		payoffFaithFast = population.a - (population.b/2);
		payoffPhil = population.a - population.b;
		payoffFast = population.a;
		
	}
	
	@Override
	public void run() {
		if (boyfriend.faithful)
			if (girlfriend.coy) { // Faith and Coy
				population.pointFaith += payoffFaithCoy; // Faith gains points
				population.pointCoy += payoffFaithCoy; // Coy gains points
					
			} else { // Faith and Fast
				population.pointFaith += payoffFaithCoy; // Faith gains points
				population.pointFast += payoffFast; // Fast gains points
			}
		else
			if (!girlfriend.coy)
				population.pointPhil += payoffPhil; // Phil gains points
				population.pointFast += payoffFast; // Fast gains points
	}
}
