
public class Woman extends Person{
	
	boolean coy;
	
	volatile Man partner = null;
	
	public synchronized void setPartner(Man partner) {this.partner = partner;}
	
	Woman(boolean coy) {
		age = 0;
		this.coy = coy;
		}
	
	boolean imCoy() {return coy;}
	
}
