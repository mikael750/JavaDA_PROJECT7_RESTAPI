package com.nnk.springboot.services;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.impl.BidListServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BidTests {

	@Mock
	BidListRepository bidListRepository;

	@InjectMocks
	BidListServiceImpl bidListService;

	static List<BidList> bidListing;
	static BidList firstBid;
	static BidList secondBid;

	@BeforeClass
	public static void setUpTests() {

		firstBid = new BidList();
		firstBid.setAccount("Account Test");
		firstBid.setType("Type test");
		firstBid.setBidListId(1);
		firstBid.setBidQuantity(10d);

		secondBid = new BidList();
		secondBid.setAccount("Spare Account");
		secondBid.setType("Other Type");
		secondBid.setBidListId(2);
		secondBid.setBidQuantity(20d);

		bidListing = new ArrayList<>();
		bidListing.add(firstBid);
		bidListing.add(secondBid);

	}

	@Test
	public void findAllBid_ShouldReturn_List() {

		// Given
		when(bidListRepository.findAll()).thenReturn(bidListing);

		// When
		List<BidList> response = bidListService.findAllBid();

		// Then
		assertEquals(10d, response.get(0).getBidQuantity(), 0.1);
		assertEquals(20d, response.get(1).getBidQuantity(), 0.1);
		assertEquals(2, response.size());
	}

	@Test
	public void findByBidListId_ShouldReturn_firstBid() {

		// Given
		when(bidListRepository.findByBidListId(1)).thenReturn(firstBid);

		// When
		BidList response = bidListService.findByBidListId(1);

		// Then
		assertEquals("Account Test", response.getAccount());
		assertEquals("Type test", response.getType());
	}

	@Test
	public void saveBid_ShouldReturn_True() {

		// Given

		// When
		bidListService.saveBid(firstBid);

		// Then
		verify(bidListRepository, times(1)).save(firstBid);
	}

	@Test
	public void checkIfIdExists_ShouldReturn_True() {

		// Given
		when(bidListRepository.existsById(1)).thenReturn(true);

		// When
		boolean response = bidListService.checkIfIdExists(1);

		// Then
		assertTrue(response);
		assertEquals(1, firstBid.getBidListId().intValue());

	}

	@Test
	public void deleteBid_ShouldReturn_True() {

		// Given
		when(bidListRepository.findByBidListId(2)).thenReturn(secondBid);

		// When
		bidListService.deleteBid(secondBid);

		// Then
		verify(bidListRepository, times(1)).delete(secondBid);
	}

}
