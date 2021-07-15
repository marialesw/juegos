package models;

public class Cell {

	private int id;
	private CellType type;
	private ShipType shipOccupied;

	public Cell(int id, CellType type) {
		this.id = id;
		this.type = type;
		this.shipOccupied = null;
	}
	
	public int getId() {
		return id;
	}
	
	public CellType getType() {
		return type;
	}
	
	public ShipType getShipOccupied() {
		return shipOccupied;
	}
	
	public void setType(CellType type) {
		this.type = type;
	}
	
	public void setShipOccupied(ShipType shipOccupied) {
		this.shipOccupied = shipOccupied;
	}
}