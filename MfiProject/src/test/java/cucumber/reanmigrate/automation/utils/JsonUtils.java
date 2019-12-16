package cucumber.reanmigrate.automation.utils;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	public static <T> List<T> readJsonAsList(String json, TypeReference<List<T>> t) throws Exception {
		try {
			ObjectMapper mapper = new ObjectMapper();
			List<T> list = mapper.readValue(json, t);
			return list;
		} catch (Exception e) {
			throw new Exception("Error occurred while reading json from file", e);
		}
	}

}
