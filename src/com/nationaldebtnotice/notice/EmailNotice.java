package com.nationaldebtnotice.notice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.nationaldebtnotice.config.Config;
import com.nationaldebtnotice.entity.Item;

public class EmailNotice extends Notice {
	public String subjectPrefix = "����ƾ֤ʽ��ծ��Ϣ";
	public void notice(List<Item> latestItems){
		StringBuffer bs = new StringBuffer();
	//	StringBuffer htmlContent = new StringBuffer();
		for (Item item : latestItems){
			bs.append("����:");
			bs.append(item.name);
			bs.append("\r\n<br/>\r\n");
			bs.append("��ַ:");
			bs.append(item.link);
			bs.append("\r\n<br/><br/>\r\n");
		//    String content = Http.getContent(item.link);
	    //    htmlContent.append(content);
	    //    htmlContent.append("\r\n<br/><br/>\r\n");
		}
		
		String emailContent = bs.toString();
		SimpleDateFormat sFormat = new SimpleDateFormat("(yyyy/MM/dd HH:mm:ss)");
		String subject = subjectPrefix + sFormat.format(new Date());
		System.out.println(emailContent);
		
		Email email = new Email();
		email.sendMail(Config.recipient, subject, emailContent);
	}

}
