package dictionary_package;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dictionary {

	private int totalWords = 0;
	private Map <String, String> data = new HashMap<String, String>();

	public String[] getKeys(){
		String[] keys = new String[data.size()];
		data.keySet().toArray(keys);
		return keys;
	}
	
	public String getDefinition(String word){
		String definition = this.data.get(word);
		if (definition == null) {
			return "-1";
		}
		return definition;
	}
	
	public String[] getWordsAsArray(){
		String[] result = new String[data.size()];
		int counter = 0;
		for (String word : this.data.keySet()) {
			result[counter] = word;
			counter++;
		}
		Arrays.sort(result);
		return result;
	}
	
	public void readCsv(String path){
		this.data = new HashMap<String,String>();
		String csvFile = path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 		br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 			String[] word = line.split(cvsSplitBy);
				this.data.put(word[0], word[1]);				
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
