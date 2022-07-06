import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class startSimulation {
	
	ArrayList<ArrayList<Integer>> start() throws InterruptedException, NoSuchSexException {
		Population population = new Population(4000, 15, 20, 3, 70, 8);
		int years = 100;
		
		ArrayList<ArrayList<Integer>> data = new ArrayList<ArrayList<Integer>>();
		
		Man man = null;
		Woman woman = null;
		
		boolean flag = true;
		
		for(int i = 0; i < years; i++) {
			
			for(int j = 0; j < population.men.size(); j++)
				population.men.get(j).age++;
			
			for(int k = 0; k < population.women.size(); k++)
				population.women.get(k).age++;
			
			population.growing();
			ExecutorService executorService = Executors.newCachedThreadPool();
			long startTime = System.currentTimeMillis();
			
			while (flag) {
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
				
				if(man.partner != null);{
					population.removePerson(man.partner);
				} 
				{
					try {
						woman = population.pickGirl();
						if(woman.partner != null){
							population.addPerson(man);
							population.addPerson(woman);
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
				
				executorService.submit(new Flirt(man, woman, population));
				long estimatedTime = System.currentTimeMillis() - startTime;
				if(estimatedTime>100) flag = false;
			}
			
			executorService.shutdown();
			System.out.println("End of transmition. There are left: ");
			population.info();
			
			flag = true;
		}
		
		return data;
		
	}
	
	public static void main(String[] args) throws InterruptedException, NoSuchSexException {
		startSimulation miracolo = new startSimulation();
		ArrayList<ArrayList<Integer>> test = miracolo.start();
		
	}

}
