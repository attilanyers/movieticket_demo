package com.company.movieticket.config;

import com.company.movieticket.dto.DirectionEnum;
import com.company.movieticket.dto.Order;
import com.company.movieticket.exception.BaseException;
import com.company.movieticket.exception.ErrorEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts URL query parameter string to Order object. e.g. "id:DESC" -> new Order("id",
 * Direction.DESC);
 */
@Component
public class StringToOrderConverter implements Converter<String, Order> {
    Pattern PATTERN = Pattern.compile("([a-zA-Z][a-zA-Z0-9]*):(DESC|ASC)");

    @Override
    public Order convert(String source) {
        Matcher matcher = PATTERN.matcher(source);
        boolean match = matcher.matches();
        if (!match || !source.equals(matcher.group(0))) {
            throw new BaseException(
                    ErrorEnum.BAD_REQUEST, "Cannot convert parameter to Order: " + source);
        }
        Order order = new Order();
        order.setProperty(matcher.group(1));
        order.setDirection(DirectionEnum.valueOf(matcher.group(2)));
        return order;
    }
}
