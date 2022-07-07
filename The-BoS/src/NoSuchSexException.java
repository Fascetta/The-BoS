class NoSuchSexException extends Exception {
	String text =  "There are no more women!";
	NoSuchSexException(String text){
		System.out.println(text);
	}
}
