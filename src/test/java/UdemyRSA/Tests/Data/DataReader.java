package UdemyRSA.Tests.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	String fileSeparator = FileSystems.getDefault().getSeparator();
	Path dataPath = Paths.get("src","test","java","UdemyRSA","Tests","Data");

	public List<HashMap<String,String>> getJsonDataToMap(String jsonFileName) throws IOException {
		
		//read jsonFile to String
		String jsonContent = FileUtils.readFileToString(
//				new File(System.getProperty("user.dir")+"\\src\\test\\java\\UdemyRSA\\Tests\\Data\\PurchaseOrder.json"),
				new File(System.getProperty("user.dir")+fileSeparator+dataPath+fileSeparator+jsonFileName),
				StandardCharsets.UTF_8);

		
		//Need to convert String to HashMap: "Jackson Databind"
		ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
	}

}
