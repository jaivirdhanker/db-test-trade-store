package com.db.service;

import com.db.entity.Trade;

public interface TradeService {
	public boolean store(Trade t);
	public boolean isValidTrade(Trade t);

}
