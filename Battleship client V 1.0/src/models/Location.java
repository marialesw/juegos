package models;

public class Location {

	private int pointX;
	private int pointY;
	
	public Location(int pointX, int pointY) {
		this.pointX = pointX;
		this.pointY = pointY;
	}
	
	public int getPointX() {
		return pointX;
	}
	
	public int getPointY() {
		return pointY;
	}
	
	public void setPointX(int pointX) {
		this.pointX = pointX;
	}
	
	public void setPointY(int pointY) {
		this.pointY = pointY;
	}
}