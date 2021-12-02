package com.epam.tat.listprocessor.validation;

import com.epam.tat.listprocessor.exception.ListProcessorException;
import com.epam.tat.listprocessor.impl.ListProcessor;

import java.util.List;

public class ListProcessorValidator {

    public static void listInitialValidation(List<String> list){
        if (list == null) {
            throw new ListProcessorException("null as list");
        }
        if (list.isEmpty()) {
            throw new ListProcessorException("list is empty");
        }
    }
}
