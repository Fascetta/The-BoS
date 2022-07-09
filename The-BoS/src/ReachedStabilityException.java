public class ReachedStabilityException extends Exception{
    //coy: 83%, faith: 62% with these parameters
    ReachedStabilityException(int faithS, int coyS){
        System.out.println("Stability is reachable!");
        System.out.println("Faithful men: " + faithS + "%");
        System.out.println("Coy women: " + coyS + "%");
        System.exit(0);
    }
}
