package com.codejam.connection;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.MappingJsonFactory;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.codejam.trade.Trade;

public class Johnson {
	public final static String JohnsonHeader = "{\n\"team:\" \"TEAMNAME\",\n\"destination:\" : \"a376855@rmqkr.net\",\n\"transactions\" : [";
	public final static String JohnsonTail = "]\n}";

	public static String convertTradeToJohnson(Trade pJohnson) {
		String returner = "{\n";
		returner = returner + "\"time\" : \"" + pJohnson.time + "\",\n";
		returner = returner + "\"type\" : \""
				+ pJohnson.type.toString().toLowerCase() + "\",\n";
		returner = returner + "\"price\" : \"" + pJohnson.price + "\",\n";
		returner = returner + "\"manager\" : \"" + pJohnson.manager + "\",\n"; // TODO
		returner = returner + "\"strategy\" : \"" + pJohnson.strategyType
				+ "\",\n";
		returner = returner + "}";
		return returner;
	}

	public static void printHeader() {
		System.out.println(JohnsonHeader);
	}

	public static void printTail() {
		System.out.println(JohnsonTail);
	}

	public static String JohnsonPOST(String pData) throws IOException {
		saveJsonToFile(pData);
		try{
			String urlParameters = "param1: a&param2: b";
			String request = "https://stage-api.esignlive.com/aws/rest/services/codejam";
			//String request = "http://www.tlwr.org/codejam/johnson.php";
			URL url = new URL(request);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization",
					"Basic Y29kZWphbTpBRkxpdGw0TEEyQWQx");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setUseCaches(false);
	
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.writeBytes(pData);
			//System.out.println(pData);
			wr.flush();
	
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuilder output = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				output.append(inputLine);
			}
	
			connection.disconnect();
	
			return parseJson(output.toString());
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong during the reporting we had a socket time out", "Error" , JOptionPane.ERROR_MESSAGE);
			return "Error";
		}

	}

	public static String parseJson(final String str) {

		JsonFactory f = new MappingJsonFactory();
		// loop until token equal to "}"
		try {
			JsonParser jParser = f.createJsonParser(str);
			while (jParser.nextToken() != JsonToken.END_OBJECT) {

				String fieldname = jParser.getCurrentName();
				if ("ceremonyId".equals(fieldname)) {

					// current token is "name",
					// move to next, which is "name"'s value
					jParser.nextToken();
					return jParser.getText(); // display mkyong

				}
			}
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void saveJsonToFile(String json) {
		try
		{
			FileWriter fstream = new FileWriter("codejam.json", false);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(json);
			// Close the output stream
			out.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
