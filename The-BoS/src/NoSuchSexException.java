class NoSuchSexException extends Exception {
	
	String sex;
	
	NoSuchSexException(String sex){
		this.sex = sex;
	}
}
