package com.db.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.db.entity.Trade;
import com.db.exceptation.TradeStoreException;
import com.db.store.trade.TradeStore;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TradeServiceImpl implements TradeService{
	
	@Autowired
	TradeStore tradeStore;
	

	public void setTradeStore(TradeStore tradeStore) {
		this.tradeStore = tradeStore;
	}

	@Override
	public boolean store(Trade t) {
		// TODO Auto-generated method stub
		if(!isValidTrade(t)) {			
			throw new TradeStoreException("Trade does not have vaild version");
		}
		tradeStore.storeTrade(t);
		return true;
	}

	@Override
	public boolean isValidTrade(Trade trade) {	
		Optional<Integer> maxTradeVersion=tradeStore.findMaxVerionByTradeID(trade.getTradeId());
		return (maxTradeVersion.isEmpty() ? true :
			maxTradeVersion.get().intValue() <= trade.getTradeVersion());
	}
	
	
	
}
