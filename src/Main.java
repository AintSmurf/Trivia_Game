import java.net.HttpURLConnection;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
//import java.awt.Font;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.io.IOException;

import java.util.Random;
import javax.swing.JButton;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {

	public static void main(String[] args) {
		// initialize to store
		StringBuffer reponseContent = new StringBuffer();

		// get the parsed data
		reponseContent = ConnectToTheWeb();
		Trivia_Builder Trivia_array[] = parse(reponseContent.toString());

		// Start The Game
		StartTheGame f = new StartTheGame(Trivia_array);
	}

//initialize connection to the URL 	
	private static HttpURLConnection connection;

	public static StringBuffer ConnectToTheWeb() {
//  First Method	
		BufferedReader reader;
		String line;
		StringBuffer reponseContent = new StringBuffer();

		try {
			// "https://opentdb.com/api.php?amount=20&category=21&difficulty=easy"
			// "https://opentdb.com/api.php?amount=10&type=boolean"
			// "https://opentdb.com/api.php?amount=20&type=multiple"
			URL url = new URL("https://opentdb.com/api.php?amount=20&category=21&difficulty=easy");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(5000);
			connection.setReadTimeout(5000);

			// connection status 200 - 299 is connected
			int status = connection.getResponseCode();
			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					reponseContent.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while ((line = reader.readLine()) != null) {
					reponseContent.append(line);
				}
				reader.close();
			}

		} catch (MalformedURLException e) {
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Couldnt Get The URL");
		} finally {
			connection.disconnect();
		}

// Second Method

//			HttpClient client = HttpClient.newHttpClient();
//			HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://opentdb.com/api.php?amount=20&category=21&difficulty=easy")).build();
//			client.sendAsync(request, HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).thenAccept(Main::parse).join();
		return reponseContent;
	}

// build the Trivia Questions
	public static Trivia_Builder[] parse(String responseBody) {
		JSONObject jsonobj = new JSONObject(responseBody);
		JSONArray questions = jsonobj.getJSONArray("results");
		Trivia_Builder Trivia_array[] = new Trivia_Builder[questions.length()];
		String category = null;
		String type = null;
		String question = null;
		String correct_answer = null;
		JSONArray incorrect_answers = null;
		int questionNumber = 0;

		for (int i = 0; i < questions.length(); i++) {

			// pull all the data from the jsonArray with jsonObject
			JSONObject jsonob = questions.getJSONObject(i);
			questionNumber = i + 1;
			category = jsonob.getString("category");
			type = jsonob.getString("type");
			question = jsonob.getString("question");
			correct_answer = jsonob.getString("correct_answer");
			incorrect_answers = jsonob.getJSONArray("incorrect_answers");

			// send the data to the class to customize the trivia questions

			Trivia_Builder tb = new Trivia_Builder(category, type, question, correct_answer, incorrect_answers);
			Trivia_array[i] = tb;
		}
		return Trivia_array;
	}
}
