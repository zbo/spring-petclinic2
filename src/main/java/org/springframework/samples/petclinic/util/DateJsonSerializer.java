package org.springframework.samples.petclinic.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.LocalDate;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class DateJsonSerializer extends JsonSerializer<LocalDate> {
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void serialize(LocalDate date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(date.toString());
    }
}
