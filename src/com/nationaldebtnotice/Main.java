package com.nationaldebtnotice;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.feima.Http;
import com.nationaldebtnotice.algorithm.NationalDebtAlg;
import com.nationaldebtnotice.config.Config;
import com.nationaldebtnotice.config.Persistence;
import com.nationaldebtnotice.entity.Item;
import com.nationaldebtnotice.notice.EmailNotice;
import com.nationaldebtnotice.parser.ItemParser;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				String content = Http.getContent(Config.webUrl);
				ItemParser itemParser = new ItemParser();
				List<Item> items = itemParser.action(content, Config.webUrl);
				NationalDebtAlg alg = new NationalDebtAlg();
				List<Item> latestItems = alg.getLatestInfo(items);

				
				if (latestItems != null && !latestItems.isEmpty()){
				   Persistence p =new Persistence();
				   p.persistLatestItems(latestItems);
				   System.out.println(latestItems);
				   EmailNotice emailNotice = new EmailNotice();
				   emailNotice.notice(latestItems);
				}
			}
		}, 0, 1000 * 60 * 60 * 2);
	
	}

}
