package Util;

public class Tuple<A,B> {
	private A first;
	private B second;
	
	public Tuple(A first, B second){
		this.first = first;
		this.second = second;
	}
	
	public Tuple() {
	}

	public A getFirst(){
		return this.first;
	}
	
	public B getSecond(){
		return this.second;
	}
	
	@Override
	public boolean equals(Object o){
		if (!(o instanceof Tuple)){
			return false;
		}
		@SuppressWarnings("unchecked")
		Tuple<A,B> tup = (Tuple<A,B>) o;
		if (this.first != tup.getFirst() || this.second != tup.getSecond()){
			return false;
		}
		return true;
	}
	
	public int hashCode(){
		return first.hashCode() + second.hashCode();
	}
}
