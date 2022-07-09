public class TypeHasDiedException extends Exception{
    TypeHasDiedException(){
        System.out.println("A type has died, stopping simulation");
        System.exit(0);
    }
}
