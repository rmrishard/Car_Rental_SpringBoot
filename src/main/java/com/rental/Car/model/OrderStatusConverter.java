package com.rental.Car.model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusConverter implements AttributeConverter<OrderStatus, String> {

    @Override
    public String convertToDatabaseColumn(OrderStatus status) {
        return status == null ? null : status.name();
    }

    @Override
    public OrderStatus convertToEntityAttribute(String dbValue) {
        if (dbValue == null) {
            return null;
        }
        return OrderStatus.valueOf(dbValue.trim().toUpperCase());
    }
}
