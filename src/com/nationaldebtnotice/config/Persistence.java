package com.nationaldebtnotice.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.nationaldebtnotice.entity.Item;

public class Persistence {
	
	public void persistLatestItems(List<Item> items){
		PrintWriter pw = null;
		try {
			File localFolder = new File("local");
			if (!localFolder.exists()){
				localFolder.mkdir();
			}
			File localFile = new File("local/localitems.per");
		    pw = new PrintWriter(localFile, "GBK");
			for(Item item : items){
				pw.println(item.name);
				pw.println(item.link);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null){
				pw.close();
			}
		}
	}
	
	public void persistLatestWebPage(String content){
		PrintWriter pw = null;
		try {
			File localFile = new File("local/local.html");
		    pw = new PrintWriter(localFile, "GBK");		
		    pw.print(content);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pw != null){
				pw.close();
			}
		}
	}

}
