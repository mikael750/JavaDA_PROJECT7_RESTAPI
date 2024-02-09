package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.impl.RuleNameServiceImpl;
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
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuleTests {

	@Mock
	RuleNameRepository ruleNameRepository;

	@InjectMocks
	RuleNameServiceImpl ruleNameService;

	static List<RuleName> ruleList;
	static RuleName firstRule;
	static RuleName secondRule;

	@BeforeClass
	public static void setUpTests() {

		firstRule = new RuleName();
		firstRule.setId(1);
		firstRule.setName("Rule Name");
		firstRule.setDescription("Description");
		firstRule.setJson("Json");
		firstRule.setTemplate("Template");
		firstRule.setSqlStr("SQL");
		firstRule.setSqlPart("SQL Part");

		secondRule = new RuleName();
		secondRule.setId(2);
		secondRule.setName("2nd Rule");
		secondRule.setDescription("SomeDescription");
		secondRule.setJson("JsonXml");
		secondRule.setTemplate("TemplateEx");
		secondRule.setSqlStr("SQL2");
		secondRule.setSqlPart("SQL Part2");

		ruleList = new ArrayList<>();
		ruleList.add(firstRule);
		ruleList.add(secondRule);
	}


	@Test
	public void findAll_ShouldReturn_List() {

		// Given
		when(ruleNameRepository.findAll()).thenReturn(ruleList);

		// When
		List<RuleName> response = ruleNameService.findAllRuleName();

		// Then
		assertEquals("Rule Name", response.get(0).getName());
		assertEquals("2nd Rule", response.get(1).getName());
		assertEquals(2, response.size());
	}

	@Test
	public void findById_ShouldReturn_firstRule() {

		// Given
		when(ruleNameRepository.findById(1)).thenReturn(Optional.ofNullable(firstRule));

		// When
		RuleName response = ruleNameService.findByRuleNameId(1);

		// Then
		assertEquals("Description", response.getDescription());
	}

	@Test
	public void saveRating_ShouldReturn_True() {

		// Given

		// When
		ruleNameService.saveRuleName(firstRule);

		// Then
		verify(ruleNameRepository, times(1)).save(firstRule);
	}

	@Test
	public void checkIfIdExists_ShouldReturn_True() {

		// Given
		when(ruleNameRepository.existsById(1)).thenReturn(true);

		// When
		boolean response = ruleNameService.checkIfIdExists(1);

		// Then
		assertTrue(response);
		assertEquals(1, firstRule.getId().intValue());
	}

	@Test
	public void deleteRuleName_ShouldReturn_True() {

		// Given
		when(ruleNameRepository.findById(2)).thenReturn(Optional.ofNullable(secondRule));

		// When
		ruleNameService.deleteRuleName(secondRule);

		// Then
		verify(ruleNameRepository, times(1)).delete(secondRule);
	}
}
