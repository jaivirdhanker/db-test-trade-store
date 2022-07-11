package com.db.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import com.db.entity.Trade;
import com.db.exceptation.TradeStoreException;
import com.db.store.trade.TradeStore;

public class TradeServiceImpTest {
	
	TradeServiceImpl tradeService;
	
	@Test
	public void store() {
		tradeService = new TradeServiceImpl();
		TradeStore tradeStore = new TradeStore();
		tradeService.setTradeStore(tradeStore);
		
		Trade trade = new Trade();	
		trade.setTradeId("abc");
		trade.setTradeVersion(1);
		tradeService.store(trade);
		
		/*
		 * Trade trade2 = new Trade(); trade2.setTradeId("abc");
		 * trade2.setTradeVersion(3);
		 * 
		 * tradeService.store(trade2);
		 * 
		 * 
		 * Trade trade3 = new Trade(); trade3.setTradeId("abc");
		 * trade3.setTradeVersion(2);
		 * 
		 * tradeService.store(trade3);
		 */
		
	}
	
	
	@Test
	public void storeWithExceptionValidFailure() {
		tradeService = new TradeServiceImpl();
		TradeStore tradeStore = new TradeStore();
		tradeService.setTradeStore(tradeStore);
		
		Trade trade = new Trade();	
		trade.setTradeId("abc");
		trade.setTradeVersion(1);
		tradeService.store(trade);
		
		
		 Trade trade2 = new Trade(); trade2.setTradeId("abc");
		 trade2.setTradeVersion(3);
		 
		 tradeService.store(trade2);
		 
		 
		 Trade trade3 = new Trade(); trade3.setTradeId("abc");
		 trade3.setTradeVersion(2);
		// tradeService.store(trade3);
		 TradeStoreException exceptation=assertThrows(TradeStoreException.class, ()->{tradeService.store(trade3);});
		 assertEquals(exceptation.getMessage(), "Trade does not have vaild version");
		 //assertThrowsExactly(TradeStoreException.class, tradeService.store(trade3));
		
		
	}

}
