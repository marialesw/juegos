package persistence;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import models.Location;
import models.Ship;

public class JSONPersistence {

	public static String writeShipInJSON(Ship ship) {
		String prettyJsonString = "";
		try {
			JsonObject object = new JsonObject();
			object.addProperty("LocX", ship.getLocation().getPointX());
			object.addProperty("LocY", ship.getLocation().getPointY());
			object.addProperty("ShipType", ship.getType().name());					
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			prettyJsonString = gson.toJson(object);
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "No seleciono barco");
		}
		return prettyJsonString;			
	}
	
	public static String writeLocationInJSON(Location location) {
		JsonObject object = new JsonObject();
		object.addProperty("LocX", location.getPointX());
		object.addProperty("LocY", location.getPointY());
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJsonString = gson.toJson(object);
		return prettyJsonString;
	}
}