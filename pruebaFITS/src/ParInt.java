
public class ParInt {
	
	private int i;
	private int j;
	
	
	public ParInt(int a, int b){
		
		this.i=a;
		this.j=b;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	public int getI() {
		return i;
	}
	public void setJ(int j) {
		this.j = j;
	}
	public int getJ() {
		return j;
	}
	public void print() {
		System.out.print(i + ", " + j);
	}

}
