package com.example.virusdb.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class JsonDateSerializer extends JsonSerializer<Date> {
    private static final String dateFormatString = "dd/MM/yyyy";
    private static final DateFormat dateFormat = new SimpleDateFormat(dateFormatString);
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(dateFormat.format(value));
    }
}
