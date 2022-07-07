import java.util.Random;
public class startSimulation {
	
	void start() throws InterruptedException, NoSuchSexException {
		Population population = new Population(1, 15, 20, 3, 60);
		int years = 100;
		
		Man boyfriend = null;
		Woman girlfriend = null;

		Random random = new Random();
		
		System.out.println("Beginning...");
		System.out.println();
		population.info();
		
		for(int i = 1; i <= years; i++) {
			for(int j = 0; j < population.men.size(); j++)
				population.men.get(j).age++;
			
			for(int k = 0; k < population.women.size(); k++)
				population.women.get(k).age++;
			
			population.growing();
			
			for(int m = 0; m < population.men.size(); m ++) {
				boyfriend = population.men.get(m);
				
				try {
					girlfriend = population.pickGirl();
				} catch (NoSuchSexException e) {
				} catch (Exception e){
					e.printStackTrace();
				}
				
				Flirt fikifiki = new Flirt(boyfriend, girlfriend, population);
				fikifiki.run();
			}
			
			population.nextGeneration();
			
			System.out.println("End of year " + i + ". There are left: ");
			population.info();
		
		}
	}
	
	public static void main(String[] args) throws InterruptedException, NoSuchSexException {
		startSimulation miracolo = new startSimulation();
		miracolo.start();
	}

}
