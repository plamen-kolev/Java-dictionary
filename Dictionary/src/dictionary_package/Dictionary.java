package dictionary_package;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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

	// https://gist.github.com/madan712/3912272
	public void readXls(String path){
		InputStream ExcelFileToRead = null;
		try {
			ExcelFileToRead = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
			XSSFSheet sheet=wb.getSheetAt(0);
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()){
				XSSFRow row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				
				
				int content = 0;
				while (cells.hasNext())
				{
					XSSFCell cell = (XSSFCell) cells.next();
					String word = cell.getStringCellValue();
					
					cell = (XSSFCell) cells.next();
					String definition = cell.getStringCellValue();
					
					cell = (XSSFCell) cells.next();
					String clarification = cell.getStringCellValue();
					
					
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public static void main(String[] args) {
		Dictionary d = new Dictionary();
		d.readXls(System.getProperty("user.dir")+ "/data.xlsx");
	}

}
