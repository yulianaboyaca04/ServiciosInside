//package com.inside.persistence;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
//
//public class JsonManager {
//
//	public static int countVideos() throws FileNotFoundException {
//		BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/inside/data/youtube.json"));
//		Gson gson = new Gson();
//		JsonObject object = gson.fromJson(br, JsonObject.class);
//
//		// para obtener directamente como int
//		// System.out.println(object.get("pageInfo").getAsJsonObject().get("totalResults").getAsInt()*100);
//
//		// primero obtener objeto y luego el array y si hay objetos obtengo este
//		System.out.println(object.get("items").getAsJsonArray());
//		JsonArray list = object.get("items").getAsJsonArray();
//		for (JsonElement jsonElement : list) {
//			System.out.println(jsonElement.getAsJsonObject().get("snippet").getAsJsonObject().get("title"));
//		}
//		return 0;
//	}
//
//	public static void readFileJson() throws FileNotFoundException {
//		BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/inside/data/youtube.json"));
//		Gson gson = new Gson();
//		JsonObject object = gson.fromJson(br, JsonObject.class);
//		System.out.println(object.get("kind").getAsString());
//		System.out.println(object.get("items").getAsJsonArray());
//		JsonArray list = object.get("items").getAsJsonArray();
//		for (JsonElement jsonElement : list) {
//			System.out.println(jsonElement.getAsJsonObject().get("snippet").getAsJsonObject().get("title"));
//		}
//	}
//
//	public static void write() throws IOException {
//		Gson gson = new Gson();
//		JsonArray datatList = new JsonArray();
//		JsonObject objProducts = new JsonObject();
//		objProducts.add("Products", gson.toJsonTree("Holii"));
//		datatList.add(objProducts);
//		FileWriter fileWriter = new FileWriter(new File("src/main/java/com/inside/data/youtube.json"));
//		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
//		bufferedWriter.write(datatList.toString());
//		bufferedWriter.close();
//		
//		
//	}
//	
//	public static void testWrite() {
//		String json1 = "[{\"dorsal\":6," + "\"name\":\"Iniesta\","
//                + "\"demarcation\":[\"Right winger\",\"Midfielder\"],"
//                + "\"team\":\"FC Barcelona\"}]";
//
//        JsonParser parser = new JsonParser();
//
//        // Obtain Array
//        JsonArray gsonArr = parser.parse(json1).getAsJsonArray();
//
//        // for each element of array
//        for (JsonElement obj : gsonArr) {
//
//            // Object of array
//            JsonObject gsonObj = obj.getAsJsonObject();
//
//            // Primitives elements of object
//            int dorsal = gsonObj.get("dorsal").getAsInt();
//            String name = gsonObj.get("name").getAsString();
//            String team = gsonObj.get("team").getAsString();
//
//            // List of primitive elements
//            JsonArray demarcation = gsonObj.get("demarcation").getAsJsonArray();
//            ArrayList<String> listDemarcation = new ArrayList();
//            for (JsonElement demarc : demarcation) {
//                listDemarcation.add(demarc.getAsString());
//            }
//        }
//           
//	}
//
//	public static int rating() throws FileNotFoundException {
//		BufferedReader br = new BufferedReader(new FileReader("src/main/java/com/inside/data/youtube.json"));
//		Gson gson = new Gson();
//		JsonArray list = gson.fromJson(br, JsonArray.class);
//		for (JsonElement jsonElement : list) {
//			System.out.println(jsonElement.getAsJsonObject().get("rating").getAsDouble());
//			System.out.println(jsonElement.getAsJsonObject().get("category"));
//		}
//		return 0;
//	}
//
//	public static void readMioJson() throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader("src/data/mio.json"));
//		Gson gson = new Gson();
//		JsonObject object = gson.fromJson(br, JsonObject.class);
//		System.out.println(object.get("response").getAsJsonObject().get("status"));
//		br.close();
//
//	}
//
//	
//}