package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

public class PerFileJSON{

	private static final String pathFile = "./files/file1.json";

	public static int read() throws FileNotFoundException, DeserializationException, IOException {
		int maxScore = 0;
		Object obj = Jsoner.deserialize(new FileReader(pathFile));
		JsonArray jsonArray = (JsonArray) obj;
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject score = (JsonObject) jsonArray.get(i);
			if (score.getInteger("Score") > maxScore) {
				maxScore = score.getInteger("Score");
			}
		}
		return maxScore;
	}

	public static void writeFileCity(int score) throws IOException {
		FileWriter file = new FileWriter(pathFile, true);//crear un nuevo archivo de escritura
		JsonObject root = new JsonObject();//determina el cuerpo del documento
		JsonArray enemiesList = new JsonArray();//es por el arrayList que esta ingresando
		JsonObject enemyObject = new JsonObject();
		enemyObject.put("Score", score);
		enemiesList.add(enemyObject);
		root.put("scores", enemiesList);
		file.write(enemiesList.toJson());
		file.flush();
		file.close();
	}
}