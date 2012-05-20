package com.nationaldebtnotice.algorithm;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.nationaldebtnotice.entity.Item;

public class NationalDebtAlg {
	
	public List<Item> getLatestInfo(List<Item> currentItems){
		List<Item> latestItems = new ArrayList<Item>();
		List<Item> localItems = loadLocalItem();
		if (localItems.isEmpty()){
			return currentItems;
		}
		for (Item item : currentItems){
			if (!item.name.equals(localItems.get(0).name)){
				latestItems.add(item);
			} else {
				break;
			}
		}
		return latestItems;
 	}
	
	public List<Item> loadLocalItem(){
		List<Item> localItems = new ArrayList<Item>();
		InputStream is =null;
		BufferedReader br = null;
		try {
		    is = new FileInputStream("local/localitems.per");
		    br = new BufferedReader(new InputStreamReader(is));  
		    
		    do{
				Item item = new Item();
				item.name = br.readLine();
				if (item.name == null){
					break;
				}
				item.link = br.readLine();
				if (item.link == null){
					break;
				}
				localItems.add(item);
			} while(true);	
		} catch (java.io.FileNotFoundException fnfe){
			fnfe.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (br != null){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return localItems;
	}

}
