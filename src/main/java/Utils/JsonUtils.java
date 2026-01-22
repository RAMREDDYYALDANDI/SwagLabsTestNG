package Utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

// this we should not import when we use GSON--> import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtils {
	
	public List<HashMap<String,String>> Jsonreader(String FileName) throws IOException {
		
		FileReader reader = new FileReader(System.getProperty("user.dir")+"/src/test/resources/"+FileName);
		List<HashMap<String,String>> data = new Gson().fromJson(reader, new TypeToken<List<HashMap<String,String>>>(){}.getType());
		reader.close();
		return data;
	}

}
