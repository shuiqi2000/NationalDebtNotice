package com.nationaldebtnotice.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.nationaldebtnotice.entity.Item;


public class ItemParser {
	public List<Item> action(String content, String webUrl){
		List<Item> items = new ArrayList<Item>();
    	Pattern pattern=Pattern.compile("right_cztu\">(.*?)</td>",Pattern.DOTALL);
    	Matcher m=pattern.matcher(content);
    	while(m.find()){
    		String itemString=m.group(1);  	
    		itemString = itemString.trim();
    		String link = parseHref(itemString, webUrl);
    		String name = parseName(itemString);
    		Item item = new Item();
    		item.link = link;
    		item.name = name;
    		items.add(item);
    		
    		System.out.println(item.link);
    		System.out.println(item.name);
    	}
    	
    	return items;
	}
	
	public String parseName(String itemString){
        String name = clearHtmlTag(itemString);
    	return clearBlank(name);
	}
	public String parseHref(String itemString, String webUrl){
        
    	String contentBuf=new String(itemString);
    	Pattern pattern=Pattern.compile("href\\s*?[=]{1,2}[\",\\s]*(.*?)\"]*");
    	Matcher m=pattern.matcher(contentBuf);
    	String link = null;
    	while(m.find()){
    		link=m.group(1);
    		link=handleOriginUrl(link, webUrl);
    		if(link==null){
    			continue;
    		}
    	}
    	
    	return link;
    }
	 public String handleOriginUrl(String link,String webUrl){
		 /*
		 if(link.indexOf("http://")!=-1){
 			;
 		 }else if(link.equals("")){
 			return null;
 		 }else if(link.charAt(0)=='/'){
 		//	link="http://"+webUrl+link;
 		 }else if(link.charAt(0)=='.'){
 			int end=webUrl.indexOf('/');
 			if(end<8){
 				link=webUrl+'/'+link.substring(2);
 			}else{
 			    link=webUrl.substring(0,end+1)+link.substring(2);
 			}
 		 }else if((link.indexOf("../"))!=-1){ 
 			int begin=0;
 			int count=1;
 			int end=0;
 			int e=0;
 			while((begin=link.indexOf("../",begin+2))!=-1){
 				count++;
 				end=begin;
 			}
 			count++;
 			
            for(e=webUrl.length()-1;e>=0;e--){
            	if(webUrl.charAt(e)=='/'){
            		count--;
            	}
            	if(count==0){
            		break;
            	}
            }
            if(e<8) return null;
            link=webUrl.substring(0,e+1)+link.substring(end+3); 
 		}else if(link.indexOf(':')==-1){
 			int end=webUrl.lastIndexOf('/');
 			if(end<8){
 				link=webUrl+'/'+link;
 			}else{
 			    link=webUrl.substring(0,end+1)+link;
 			}
 		 }
		return link;
		*/
		 if (!webUrl.endsWith("/")){
			webUrl += "/"; 
		 }
		 
		 return webUrl+link;
		 
	 }
	 
	 public String clearHtmlTag(String content){
		 return content.replaceAll("<.*?>", "");
	 }
	 
	 public String clearBlank(String name){
		 return name.replaceAll("\\s", "");
	 }

}
