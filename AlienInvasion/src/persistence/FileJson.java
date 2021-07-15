package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import models.Enemy;

public class FileJson{

	private static final String pathFile = "./files/files.json";
	
	public static void read() throws FileNotFoundException, DeserializationException, IOException {
		Object obj = Jsoner.deserialize(new FileReader(pathFile));
		JsonArray jsonArray = (JsonArray) obj;
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject enemy = (JsonObject) jsonArray.get(i);
			System.out.println(enemy.size());
		}
	}

	public static void writeFileCity(ArrayList<Enemy> enemies) throws IOException {
		FileWriter file = new FileWriter(pathFile);//crear un nuevo archivo de escritura
		JsonObject root = new JsonObject();//determina el cuerpo del documento
		JsonArray enemiesList = new JsonArray();//es por el arrayList que esta ingresando
		for (Enemy enemy : enemies) {
//			JsonObject cityObject = new JsonObject();
//			JsonObject cityInfo = new JsonObject();
//			cityInfo.put("id", enemy.getId());
//			cityInfo.put("nameCity", enemy.getNameCity());
//			cityInfo.put("quiantityTrash", enemy.getTotalQuinatityTrashCity());
//			cityInfo.put("WasteDebt", enemy.getWasteDebt());
//			cityObject.put("city_info", cityInfo);
//			enemiesList.add(cityObject);
			JsonObject enemyObject = new JsonObject();
			enemyObject.put("X", enemy.getX());
			enemyObject.put("Y", enemy.getY());
			enemyObject.put("isAlive", enemy.isAlive());
			enemyObject.put("state", enemy.getState());
			enemiesList.add(enemyObject);
		}
		root.put("citys", enemiesList);
		file.write(enemiesList.toJson());
		file.flush();
		file.close();
	}
}