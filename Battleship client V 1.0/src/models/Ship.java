package models;

public class Ship {
	
	private Location location;
	private ShipType type;
	private Orientation orientation;
	
	public Ship(Location location, ShipType type) {
		this.location = location;
		this.type = type;
		this.orientation = null;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public ShipType getType() {
		return type;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
}