package model.messages;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class SpecialStringDeserializer extends JsonDeserializer<String>{
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SpecialStringDeserializer.class.getName());
	
	@Override
	public String deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		TreeNode tree = p.getCodec().readTree(p);
        return tree.toString();
	}
	
}
