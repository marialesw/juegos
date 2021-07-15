package models;

public enum ShipType {
	AIRCRAFT_CARRIER(new Area(1,4)), SUBMARINE(new Area(1,3)), VESSEL(new Area(1,2)), BOAT(new Area(1,1)), MISSILE_STATION(new Area(1, 3));
	
	private Area area;
	
	private ShipType(Area area) {
		this.area = area;
	}
	
	public Area getArea() {
		return area;
	}
}