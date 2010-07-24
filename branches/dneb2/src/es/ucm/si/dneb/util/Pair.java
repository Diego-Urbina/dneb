package es.ucm.si.dneb.util;

public class Pair<E> {

	private E a, b;
	
	public Pair(E a, E b) {
		this.a = a;
		this.b = b;
	}

	public E getA() {
		return a;
	}

	public void setA(E a) {
		this.a = a;
	}

	public E getB() {
		return b;
	}

	public void setB(E b) {
		this.b = b;
	}

}
