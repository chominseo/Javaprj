package com.newlecture.web.entity;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Date;

public class File {
	private boolean isFile;
	private String name;
	private String type;
	private long size;
	private Date createdDate;
	
	public File(java.io.File file) {
		name = file.getName();
		size = file.length();
		
		if(file.isDirectory())
			type = "dir";
		else if (name.lastIndexOf(".") == -1)
			type="noext";
		else
		type = name.substring(name.lastIndexOf(".")+1);
		//createdDate = file.toPath();
		
		try {
			BasicFileAttributes fattr 
			= Files.readAttributes(file.toPath(), BasicFileAttributes.class);
			
			createdDate = new Date(fattr.creationTime().toMillis());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void fromFile(java.io.File file) {
		name = file.getName();
	}
	
	public String toJSON() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException {
		/*
		Field[] fields = this.getClass().getDeclaredFields();
		
		StringBuilder json = new StringBuilder();
		json.append("{");
		for(Field field : fields) {
			json.append(String.format("\"%s\":\"\"", field.getName()));
		}
		json.append("}");
		*/
		
		//System.out.println(fields[1].getName());
		
		return JSONParser.toJSON(this);
		
		//return String.format("{\"name\":\"%s\",\"type\":\"%s\",\"size\":\"%d\",\"createdDate\":\"%s\"}",name,type,size,createdDate);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Boolean getIsFile() {
		return isFile;
	}

	@Override
	public String toString() {
		return "File [name=" + name + ", type=" + type + ", size=" + size + ", createdDate=" + createdDate + "]";
	}
 
}
