package com.example.onlineshop.config;

//import com.example.onlineshop.exception.runtimeExceptions.MyException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.math.BigDecimal;

public class CustomBigDecimalDeserializer extends StdDeserializer<BigDecimal> {

    public CustomBigDecimalDeserializer() {
        super(BigDecimal.class);
    }

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText();
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException e) {
            // Võite logida vea, kuid ei viska erandit
            return null;
        }

//        String value = p.getText();
//        try {
//            return new BigDecimal(value);
//        } catch (NumberFormatException e) {
//            String fieldName = p.getCurrentName(); // get field name
//            throw new MyException("'" + p.getText() + "' must be a number", fieldName);
//        }
    }
}

