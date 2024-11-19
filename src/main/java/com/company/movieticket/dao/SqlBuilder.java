package com.company.movieticket.dao;

import com.company.movieticket.dto.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlBuilder {
    private ArrayList<String> whereConditions = new ArrayList<>();

    public void addWhereCondition(String whereCondition) {
        whereConditions.add(whereCondition);
    }

    public String getQuery(String basePart) {
        return basePart + whereCondition(whereConditions);
    }

    public String getQuery(
            String basePart,
            String additionalWhereCondition,
            List<Order> sorting,
            Map<String, String> sortingOptions,
            String defaultOrderByClause) {
        ArrayList<String> finalWhereConditions = whereConditions;
        if (additionalWhereCondition != null) {
            finalWhereConditions = new ArrayList<>(whereConditions);
            finalWhereConditions.add(additionalWhereCondition);
        }
        return basePart
                + whereCondition(finalWhereConditions)
                + orderBy(sorting, sortingOptions, defaultOrderByClause);
    }

    public String getQuery(
            String basePart, List<Order> sorting, Map<String, String> sortingOptions) {
        return getQuery(basePart, null, sorting, sortingOptions, null);
    }

    protected String createOrderByClause(
            List<Order> sorting, Map<String, String> sortingOptions, String defaultOrderByClause) {
        if (sorting == null || sorting.isEmpty()) {
            return defaultOrderByClause;
        }

        String clause =
                sorting.stream()
                        .map(
                                order -> {
                                    String sortingOption = sortingOptions.get(order.getProperty());
                                    if (sortingOption == null) {
                                        throw new IllegalArgumentException(
                                                "Illegal sorting property: " + order.getProperty());
                                    }
                                    return sortingOption + " " + order.getDirection();
                                })
                        .collect(Collectors.joining(", "));
        return clause;
    }

    protected String whereCondition(List<String> finalWhereConditions) {
        if (!finalWhereConditions.isEmpty()) {
            return " WHERE (" + String.join(") AND (", finalWhereConditions) + ")";
        }
        return "";
    }

    protected String orderBy(
            List<Order> sorting, Map<String, String> sortingOptions, String defaultOrderByClause) {
        String clause = createOrderByClause(sorting, sortingOptions, defaultOrderByClause);
        return clause == null ? "" : " ORDER BY " + clause;
    }
}
