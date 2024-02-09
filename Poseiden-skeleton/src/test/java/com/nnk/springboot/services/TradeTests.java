package com.nnk.springboot.services;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import com.nnk.springboot.services.impl.TradeServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Mock
	TradeRepository tradeRepository;

	@InjectMocks
	TradeServiceImpl tradeService;

	static List<Trade> tradeList;
	static Trade firstTrade;
	static Trade secondTrade;

	@BeforeClass
	public static void setUpTests() {

		firstTrade = new Trade();
		firstTrade.setTradeId(1);
		firstTrade.setAccount("Trade Account");
		firstTrade.setType("Type");

		secondTrade = new Trade();
		secondTrade.setTradeId(1);
		secondTrade.setAccount("Trade Again");
		secondTrade.setType("NotMyType");

		tradeList = new ArrayList<>();
		tradeList.add(firstTrade);
		tradeList.add(secondTrade);
	}

	@Test
	public void findAll_ShouldReturn_List() {

		// Given
		when(tradeRepository.findAll()).thenReturn(tradeList);

		// When
		List<Trade> response = tradeService.findAllTrade();

		// Then
		assertEquals("Trade Account", response.get(0).getAccount());
		assertEquals("Trade Again", response.get(1).getAccount());
		assertEquals(2, response.size());
	}

	@Test
	public void findById_ShouldReturn_firstTrade() {

		// Given
		when(tradeRepository.findByTradeId(1)).thenReturn(firstTrade);

		// When
		Trade response = tradeService.findTradeById(1);

		// Then
		assertEquals("Type", response.getType());
	}

	@Test
	public void saveTrade_ShouldReturn_True() {

		// Given

		// When
		tradeService.saveTrade(firstTrade);

		// Then
		verify(tradeRepository, times(1)).save(firstTrade);
	}

	@Test
	public void checkIfIdExists_ShouldReturn_True() {

		// Given
		when(tradeRepository.existsById(1)).thenReturn(true);

		// When
		boolean response = tradeService.checkIfIdExists(1);

		// Then
		assertTrue(response);
		assertEquals(1, firstTrade.getTradeId().intValue());
	}

	@Test
	public void deleteTrade_ShouldReturn_True() {

		// Given
		when(tradeRepository.findByTradeId(2)).thenReturn(secondTrade);

		// When
		tradeService.deleteTrade(secondTrade);

		// Then
		verify(tradeRepository, times(1)).delete(secondTrade);
	}
}
