package com.db.store.trade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.db.entity.Trade;

public class TradeStoreTest {
	TradeStore tradeStore;
	
	
	@Test
	public void findTradeByTradeIdVersionNumberNull() {
		tradeStore = new TradeStore();
		Trade trade = new Trade();		
		trade=tradeStore.findTradeByTradeIdVersionNumber("abc", 1);
		assertNull(trade);
		
	}
	

	@Test
	public void findTradeByTradeIdVersionNumber() {
		tradeStore = new TradeStore();
		Trade trade = new Trade();	
		trade.setTradeId("abc");
		trade.setTradeVersion(1);
		tradeStore.storeTrade(trade);
		trade=tradeStore.findTradeByTradeIdVersionNumber("abc", 1);
		assertNotNull(trade);
		assertEquals("abc", trade.getTradeId());
		assertEquals(1, trade.getTradeVersion());
		
	}
	
	@Test
	public void findMaxVerionByTradeID() {
		tradeStore = new TradeStore();
		Trade trade = new Trade();	
		trade.setTradeId("abc");
		trade.setTradeVersion(1);
		tradeStore.storeTrade(trade);		
		Trade trade2 = new Trade();	
		trade2.setTradeId("abc");
		trade2.setTradeVersion(3);
		tradeStore.storeTrade(trade2);
		Optional<Integer> maxversion=tradeStore.findMaxVerionByTradeID("abc");			
		assertEquals(3, maxversion.get());
		
	}

}
