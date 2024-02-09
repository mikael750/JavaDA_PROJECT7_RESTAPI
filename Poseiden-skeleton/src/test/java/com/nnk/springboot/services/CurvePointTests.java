package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.impl.CurvePointServiceImpl;
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
public class CurvePointTests {

	@Mock
	CurvePointRepository curvePointRepository;

	@InjectMocks
	CurvePointServiceImpl curvePointService;

	static List<CurvePoint> curvePointList;
	static CurvePoint firstCurvePoint;
	static CurvePoint secondCurvePoint;

	@BeforeClass
	public static void setUpTests() {

		firstCurvePoint = new CurvePoint();
		firstCurvePoint.setId(1);
		firstCurvePoint.setCurveId(10);
		firstCurvePoint.setTerm(10d);
		firstCurvePoint.setValue(30d);

		secondCurvePoint = new CurvePoint();
		secondCurvePoint.setId(2);
		secondCurvePoint.setCurveId(20);
		secondCurvePoint.setTerm(20d);
		secondCurvePoint.setValue(60d);

		curvePointList = new ArrayList<>();
		curvePointList.add(firstCurvePoint);
		curvePointList.add(secondCurvePoint);

	}

	@Test
	public void findAll_ShouldReturn_List() {

		// Given
		when(curvePointRepository.findAll()).thenReturn(curvePointList);

		// When
		List<CurvePoint> response = curvePointService.findAllCurvePoint();

		// Then
		assertEquals(10d, response.get(0).getCurveId(), 0.1);
		assertEquals(20d, response.get(1).getCurveId(), 0.1);
		assertEquals(2, response.size());
	}

	@Test
	public void findById_ShouldReturn_firstCurvePoint() {

		// Given
		when(curvePointRepository.findById(1)).thenReturn(Optional.ofNullable(firstCurvePoint));

		// When
		CurvePoint response = curvePointService.findByCurvePointId(1);

		// Then
		assertEquals(10d, response.getTerm(), 0.1);
	}

	@Test
	public void saveCurvePoint_ShouldReturn_True() {

		// Given

		// When
		curvePointService.saveCurvePoint(firstCurvePoint);

		// Then
		verify(curvePointRepository, times(1)).save(firstCurvePoint);
	}

	@Test
	public void checkIfIdExists_ShouldReturn_True() {

		// Given
		when(curvePointRepository.existsById(1)).thenReturn(true);

		// When
		boolean response = curvePointService.checkIfIdExists(1);

		// Then
		assertTrue(response);
		assertEquals(1, firstCurvePoint.getId().intValue());
	}

	@Test
	public void deleteCurvePoint_ShouldReturn_True() {

		// Given
		when(curvePointRepository.findById(2)).thenReturn(Optional.ofNullable(secondCurvePoint));

		// When
		curvePointService.deleteCurvePoint(secondCurvePoint);

		// Then
		verify(curvePointRepository, times(1)).delete(secondCurvePoint);
	}

}
