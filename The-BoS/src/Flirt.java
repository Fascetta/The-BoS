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
	
	

}
