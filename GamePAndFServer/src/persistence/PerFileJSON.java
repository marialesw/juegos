package persistence;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.management.openmbean.ArrayType;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import models.User;

public class PerFileJSON{

	private static final String pathFile = "./files/file1.json";

	public static ArrayList<User> read() throws FileNotFoundException, DeserializationException, IOException {
		ArrayList<User> users = new ArrayList<>();
 		Object obj = Jsoner.deserialize(new FileReader(pathFile));
		JsonArray jsonArray = (JsonArray) obj;
		for (int i = 0; i < jsonArray.size(); i++) {
			JsonObject userObject = (JsonObject) jsonArray.get(i);
			User user = new User(userObject.getString("Host"), LocalDate.parse(userObject.getString("Fecha"), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
					LocalTime.parse(userObject.getString("Hora"), DateTimeFormatter.ofPattern("dd/MM/yyyy")), userObject.getInteger("Numero"), userObject.getInteger("Intentos"));
			System.out.println(user.toString());
			users.add(user);
		}
		return users;
	}

	public static void writeFileUser(ArrayList<User> users) throws IOException {
		FileWriter file = new FileWriter(pathFile, true);//crear un nuevo archivo de escritura
		JsonObject root = new JsonObject();//determina el cuerpo del documento
		JsonArray userList = new JsonArray();//es por el arrayList que esta ingresando
		for (User user : users) {
			JsonObject userObject = new JsonObject();
			userObject.put("Host", user.getHost());
			userObject.put("Fecha", String.valueOf(user.getFecha()));
			userObject.put("Hora", String.valueOf(user.getHora()));
			userObject.put("Numero", user.getNumberGuess());
			userObject.put("Intentos", user.getNumberAttemps());

			userList.add(userObject);
		}
		
		root.put("users", userList);
		file.write(userList.toJson());
		file.flush();
		file.close();
	}
}