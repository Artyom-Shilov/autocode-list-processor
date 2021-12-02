package com.epam.tat.listprocessor.impl;

import com.epam.tat.listprocessor.IListProcessor;
import com.epam.tat.listprocessor.exception.ListProcessorException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Function Description:
 * Complete the functions below. All methods must work with list of String.
 *
 * In case of incorrect input values or inability to perform an action, the method should throw an appropriate
 * exception.
 *
 */
public class ListProcessor implements IListProcessor {

	/**
	 * Find the second by length string in a list.
	 *
	 * Ex.:
	 * From list:
	 * {"a", "aa", "aaaaa", "aaaa", "aaa"}
	 * will be return "aaaa"
	 *
	 * @param list - input data
	 * @return second by length string in the input list
	 */
	@Override
	public String getSecondStringByLength(List<String> list) {
		try {
			return list.stream()
					.sorted(Comparator.comparing(String::length).reversed())
					.skip(1)
					.findFirst()
					.get();
		} catch (Exception e){
			throw new ListProcessorException(e);
		}

	}

	/**
	 * Sort list by string length.
	 *
	 * Ex.:
	 * From list:
	 * {"a", "aa", "aaA", "aAa", "aaa", "Aa"}
	 * will be return
	 * {"a", "Aa", "aa", "aAa", "aaA", "aaa"}
	 *
	 * @param list - input data
	 * @return sort list by string length
	 */
	@Override
	public List<String> getSortedListByLength(List<String> list) {
		try {
			return list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		} catch (Exception e) {
			throw new ListProcessorException(e);
		}
	}

	List<Character> vowels = List.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o');
	List<Character> digits = List.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');

	public int calcCountOfVowels(String string){
		int countOfVowels = 0;
		for (char ch : string.toCharArray()){
			if (digits.contains(ch)){
				continue;
			}
			if (vowels.contains(ch)) {
				countOfVowels++;
			}
		}
		return countOfVowels;
	}

	/**
	 * Sort list or array by count of vowels in string.
	 * If the number of vowels in several words is the same, the order is alphabetical.
	 *
	 * Ex.:
	 * From list:
	 * {"Puma", "Nike", "Timberland", "Adidas"}
	 * will be return
	 * {"Nike", "Puma", "Adidas", "Timberland"}
	 *
	 * @param list - input data
	 * @return sort list by string length
	 */

	@Override
	public List<String> getSortedListByCountOfVowels(List<String> list) {
		try {
			return list.stream()
					.sorted(Comparator.comparing(this::calcCountOfVowels).thenComparing(String::compareTo))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ListProcessorException(e);
		}
	}

	public int calcCountOfConsonants(String string){
		int countOfConsonants = 0;
		for (char ch : string.toCharArray()){
			if (digits.contains(ch)){
				continue;
			}
			if (!vowels.contains(ch)) {
				countOfConsonants++;
			}
		}
		return countOfConsonants;
	}

	/**
	 * Sort list or array by count of consonants in string.
	 * If the number of consonants in several words is the same, the order is alphabetical.
	 *
	 * Ex.:
	 * From list:
	 * {"Puma", "Nike", "Timberland", "Adidas"}
	 * will be return
	 * {"Nike", "Puma", "Adidas", "Timberland"}
	 *
	 * @param list - input data
	 * @return sort list by string length
	 */

	@Override
	public List<String> getSortedListByCountOfConsonants(List<String> list) {
		try {
			return list.stream()
					.sorted(Comparator.comparing(this::calcCountOfConsonants).thenComparing(String::compareTo))
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new ListProcessorException(e);
		}
	}

	/**
	 * Change by places first and last symbols in each second string of list.
	 *
	 * Ex.:
	 * From list:
	 * {"Puma", "Nike", "Timberland", "Adidas"}
	 * will be return
	 * {"Puma", "eikN", "Timberland", "sdidaA"}
	 *
	 * @param list - input data
	 * @return sort list by string length
	 */
	@Override
	public List<String> changeByPlacesFirstAndLastSymbolsInEachSecondStringOfList(List<String> list) {
		StringBuilder stringBuilder = new StringBuilder();
		try {
			for (int i = 1; i < list.size(); i = i + 2) {
				String string = list.get(i);
				char first = string.charAt(0);
				char last = string.charAt(string.length() - 1);
				stringBuilder.append(string);
				stringBuilder.setCharAt(0, last);
				stringBuilder.setCharAt(string.length() - 1, first);
				list.set(i, stringBuilder.toString());
				stringBuilder.setLength(0);
			}
			return list;
		} catch (Exception e) {
			throw new ListProcessorException(e);
		}
	}

	/**
	 * Revert strings of list.
	 *
	 * Ex.:
	 * From list:
	 * {"Puma", "Nike", "Timberland", "Adidas"}
	 * will be return
	 * {"amuP", "ekiN", "dnalrebmiT", "sadidA"}
	 *
	 * @param list - input data
	 * @return sort list by string length
	 */
	@Override
	public List<String> reverseStringsOfList(List<String> list) {
		try {
			return list.stream().map(s -> new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
		} catch (Exception e) {
			throw new ListProcessorException(e);
		}
	}
}
