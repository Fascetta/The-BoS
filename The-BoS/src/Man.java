
public class Man extends Person{
	
	boolean faithful;
	
	volatile Woman partner = null;
	
	public synchronized void setPartner(Woman partner) {this.partner = partner;}
	
	Man(boolean faithful) {
		age = 0;
		this.faithful = faithful;
		}
	
	boolean imFaith() {return faithful;}

}