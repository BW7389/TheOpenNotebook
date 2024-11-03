package com.dev.notebook.enumeration.converter;

import com.dev.notebook.enumeration.Authority;
import jakarta.persistence.AttributeConverter;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RoleConverter implements AttributeConverter<Authority, String> {

    private static final Map<String, Authority> codeToAuthorityMap = Stream.of(Authority.values())
            .collect(Collectors.toMap(Authority::getValue, authority -> authority));

    @Override
    public String convertToDatabaseColumn(Authority authority) {
        return (authority != null) ? authority.getValue() : null;
    }

    @Override
    public Authority convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        Authority authority = codeToAuthorityMap.get(code);
        if (authority == null) {
            throw new IllegalArgumentException("Unknown authority code: " + code);
        }
        return authority;
    }
}