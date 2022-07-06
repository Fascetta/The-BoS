import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class startSimulation {
	
	ArrayList<ArrayList<Integer>> start() throws InterruptedException, NoSuchSexException {
		Population population = new Population(5, 15, 20, 3, 50, 8);
		int years = 100;
		
		ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		
		Man man = null;
		Woman woman = null;
		
		boolean flag = true;
		
		System.out.println("Beginning...");
		System.out.println();
		population.info();
		
		Random random = new Random();
		
		for(int i = 1; i <= years; i++) {
			
			for(int j = 0; j < population.men.size(); j++)
				population.men.get(j).age++;
			
			for(int k = 0; k < population.women.size(); k++)
				population.women.get(k).age++;
			
			population.growing();
		
			for(int p = 0; p < random.nextInt(population.countCoy); p++) {
				
				try {
					man = population.pickBoy();
				}
				catch (NoSuchSexException e) {
					System.out.println("There are no more " + e.sex + ".");
					population.info();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				
				if(man.partner != null){
					population.removePerson(man.partner);
				}
				else {
					try {
						woman = population.pickGirl();
						if(woman.partner != null){
							population.addPerson(man);
							population.addPerson(woman);
							continue;
						}
					}
					catch (NoSuchSexException e) {
						System.out.println("There are no more " + e.sex + ".");
						population.info();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}				
			Flirt prova = new Flirt(man,woman, population);
			prova.run();
			
			}

			System.out.println("End of year " + i + ". There are left: ");
			population.info();
		}
		
		
		return data;
		
	}
	
	public static void main(String[] args) throws InterruptedException, NoSuchSexException {
		startSimulation miracolo = new startSimulation();
		ArrayList<ArrayList<Integer>> test = miracolo.start();
		
	}

}
