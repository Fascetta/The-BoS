public class TypeHasDiedException extends Exception{
    String sex;
    TypeHasDiedException(){
        System.out.println("All the " + sex + " are died, stopping simulation");
        System.exit(0);
    }
}
