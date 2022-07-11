package com.db.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.db.entity.Trade;
import com.db.service.TradeService;

@RestController("/trade-store")
public class TradeStoreController {
	
	@Autowired
	TradeService tradeService;

	@PostMapping("/trade")
	public Boolean trade(@RequestBody Trade trade) {		
		return tradeService.store(trade);				
	}

}
