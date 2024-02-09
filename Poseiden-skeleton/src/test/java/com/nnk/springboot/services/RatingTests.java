package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.impl.RatingServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Mock
	RatingRepository ratingRepository;

	@InjectMocks
	RatingServiceImpl ratingService;

	static List<Rating> ratingList;
	static Rating firstRating;
	static Rating secondRating;

	@BeforeClass
	public static void setUpTests() {

		firstRating = new Rating();
		firstRating.setId(1);
		firstRating.setMoodysRating("Moodys Rating");
		firstRating.setSandPRating("Sand PRating");
		firstRating.setFitchRating("Fitch Rating");
		firstRating.setOrderNumber(10);

		secondRating = new Rating();
		secondRating.setId(2);
		secondRating.setMoodysRating("Moody 2 Rating");
		secondRating.setSandPRating("Sand 2 PRating");
		secondRating.setFitchRating("Fitch 2 Rating");
		secondRating.setOrderNumber(20);

		ratingList = new ArrayList<>();
		ratingList.add(firstRating);
		ratingList.add(secondRating);

	}

	@Test
	public void findAll_ShouldReturn_List() {

		// Given
		when(ratingRepository.findAll()).thenReturn(ratingList);

		// When
		List<Rating> response = ratingService.findAllRating();

		// Then
		assertEquals(10, response.get(0).getOrderNumber(), 0.1);
		assertEquals(20, response.get(1).getOrderNumber(), 0.1);
		assertEquals(2, response.size());
	}

	@Test
	public void findById_ShouldReturn_firstRating() {

		// Given
		when(ratingRepository.findById(1)).thenReturn(Optional.ofNullable(firstRating));

		// When
		Rating response = ratingService.findByRatingId(1);

		// Then
		assertEquals(10, response.getOrderNumber(), 0.1);
	}

	@Test
	public void saveRating_ShouldReturn_True() {

		// Given

		// When
		ratingService.saveRating(firstRating);

		// Then
		verify(ratingRepository, times(1)).save(firstRating);
	}

	@Test
	public void checkIfIdExists_ShouldReturn_True() {

		// Given
		when(ratingRepository.existsById(1)).thenReturn(true);

		// When
		boolean response = ratingService.checkIfIdExists(1);

		// Then
		assertTrue(response);
		assertEquals(1, firstRating.getId().intValue());
	}

	@Test
	public void deleteBid_ShouldReturn_True() {

		// Given
		when(ratingRepository.findById(2)).thenReturn(Optional.ofNullable(secondRating));

		// When
		ratingService.deleteRating(secondRating);

		// Then
		verify(ratingRepository, times(1)).delete(secondRating);
	}
}
