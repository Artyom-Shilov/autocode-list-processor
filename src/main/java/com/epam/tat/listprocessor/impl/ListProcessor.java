package com.epam.tat.listprocessor.impl;

import com.epam.tat.listprocessor.IListProcessor;
import com.epam.tat.listprocessor.exception.ListProcessorException;
import com.epam.tat.listprocessor.validation.ListProcessorValidator;
import java.util.*;
import java.util.stream.Collectors;

public class ListProcessor implements IListProcessor {

    @Override
    public String getSecondStringByLength(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        if (list.size() == 1) {
            throw new ListProcessorException("length is 1");
        }
        TreeSet<String> set = new TreeSet<>(Comparator.comparing(String::length));
        set.addAll(list);
        if (set.size() == 1) {
            throw new ListProcessorException("identical values");
        }
        set.pollLast();
        return set.pollLast();
    }

    @Override
    public List<String> getSortedListByLength(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        return list.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
    }

    List<Character> vowels = List.of('A', 'E', 'I', 'O', 'U', 'a', 'e', 'i', 'o');
    List<Character> digits = List.of('1', '2', '3', '4', '5', '6', '7', '8', '9', '0');

    public int calcCountOfVowels(String string) {
        int countOfVowels = 0;
        for (char ch : string.toCharArray()) {
            if (vowels.contains(ch)) {
                countOfVowels++;
            }
        }
        if (countOfVowels == 0) {
            throw new ListProcessorException("there are no vowels");
        }
        return countOfVowels;
    }

    @Override
    public List<String> getSortedListByCountOfVowels(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        return list.stream()
                .sorted(Comparator.comparing(this::calcCountOfVowels).thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }

    public int calcCountOfConsonants(String string) {
        int countOfConsonants = 0;
        for (char ch : string.toCharArray()) {
            if (digits.contains(ch)) {
                continue;
            }
            if (!vowels.contains(ch)) {
                countOfConsonants++;
            }
        }
        if (countOfConsonants == 0) {
            throw new ListProcessorException("there are no consonants");
        }
        return countOfConsonants;
    }

    @Override
    public List<String> getSortedListByCountOfConsonants(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        return list.stream()
                .sorted(Comparator.comparing(this::calcCountOfConsonants).thenComparing(String::compareTo))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> changeByPlacesFirstAndLastSymbolsInEachSecondStringOfList(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        if (list.size() == 1) {
            throw new ListProcessorException("size is 1");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < list.size(); i = i + 2) {
            String string = list.get(i);
            if (string.length() < 2) {
                continue;
            }
            char first = string.charAt(0);
            char last = string.charAt(string.length() - 1);
            stringBuilder.append(string);
            stringBuilder.setCharAt(0, last);
            stringBuilder.setCharAt(string.length() - 1, first);
            list.set(i, stringBuilder.toString());
            stringBuilder.setLength(0);
        }
        return list;
    }

    @Override
    public List<String> reverseStringsOfList(List<String> list) {
        ListProcessorValidator.listInitialValidation(list);
        return list.stream().map(s -> new StringBuilder(s).reverse().toString()).collect(Collectors.toList());
    }
}
