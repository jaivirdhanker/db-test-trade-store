package com.db.store.trade;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.db.entity.Trade;

@Service
@EnableScheduling
public class TradeStore {
	
	private Map<String, Map<Integer, Trade>> tradeStore = new HashMap();

	public Optional<Integer> findMaxVerionByTradeID(String tradeID) {

		Map<Integer, Trade> tradesByTradeID = Optional.ofNullable(tradeStore.get(tradeID)).orElse(new HashMap<Integer, Trade>());
		Comparator<Integer> comparator = (o1, o2) -> {
			return -1*o1.compareTo(o2);
		};
		
		return Optional.ofNullable(tradesByTradeID).get().keySet().stream().sorted(comparator).findFirst();

	}
	
	public void storeTrade(Trade trade) {
		Map<Integer, Trade> tradesByTradeID =Optional.ofNullable(tradeStore.get(trade.getTradeId())).orElse(new HashMap<Integer, Trade>());
		tradesByTradeID.put(trade.getTradeVersion(), trade);
		tradeStore.put(trade.getTradeId(), tradesByTradeID);		
	}
	
	public Trade findTradeByTradeIdVersionNumber( String TradeID, Integer versionNumber) {
		Map<Integer, Trade> tradesByTradeID =Optional.ofNullable(tradeStore.get(TradeID)).orElse(new HashMap<Integer, Trade>());
		return tradesByTradeID.get(versionNumber);
	}
	
	@Scheduled(cron ="0 0 0 * * *")
	public void updateExpiredFlag() {
		
		Set<String> tradeIds=tradeStore.keySet();
		for (Iterator iterator = tradeIds.iterator(); iterator.hasNext();) {
			String tradeId = (String) iterator.next();
			Map<Integer, Trade> tradesByTradeID=tradeStore.get(tradeId);
			for (Trade trade : tradesByTradeID.values()) {
				if(trade.getMaturity().before(new Date())) {
					trade.setExpired("Y");
				}
			}
			
		}
		
		
	}
	
}
