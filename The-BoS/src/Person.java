
public abstract class Person {
	
	int age;
	
	synchronized int getAge() {return age;}
	
	synchronized void getAge(int age) {this.age = age;}
	
}
