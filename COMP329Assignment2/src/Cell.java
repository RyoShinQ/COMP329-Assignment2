
public class Cell {
	private int occupied;		// 0 is empty, 1 is obstacle, 2 is a victim, 3 is hospital
	private boolean critical;	//true is critical, false is low-priority
	
	public Cell() {
		occupied = 0;
		critical = false;
	}
	
	public Cell(int occupied, boolean critical) {
		this.occupied = occupied;
		this.critical = critical;
	}
}
