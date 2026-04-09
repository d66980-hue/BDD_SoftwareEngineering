package com.test.springbdd;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CostController {

	Logger log = LoggerFactory.getLogger(this.getClass());
	

	@Autowired
	Environment env;
	
	@GetMapping("/getCost")
	public ResponseEntity<Cost> getCost() {
		log.info("Called /getCost");
		//return ResponseEntity.ok(new Product("Test"));
		return ResponseEntity.ok(new Cost("80Euros-Testing"));
	}

	/**
	 * This method is added just to test the persistent shared storage for Azure app
	 * service by creating some files
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/cost/createFile/{fileName}")
	public ResponseEntity<Map<String, Object>> createFile(@PathVariable String fileName) throws Exception {
		log.info("Called /cost/createFile");
		File dir = new File("/home");
		dir.mkdirs();
		File file = new File(dir, fileName);
		boolean fileCreated = file.createNewFile();
		Map<String, Object> data = new HashMap<>();
		data.put("files List", dir.listFiles());
		data.put("Message", "File Created " + fileCreated + file.getAbsolutePath());
		data.put("FromKeyValue",env.getProperty("connectionString"));
		return ResponseEntity.ok(data);
	}

}
