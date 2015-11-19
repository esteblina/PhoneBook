package net.ukr.steblina.config.file;


import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import net.ukr.steblina.config.BasicDataConfig;


@Component
@Profile("file")
@PropertySource("file:${lardi.conf}")
public class FileDataConfig implements BasicDataConfig {
	@Value("${file.path}")
	private String path;
	
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
	@Bean
	public File getFile() throws IOException{
		File file=new File(path);
		if(!file.exists()){
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		} 
		return file;
	}
	
}
