package me.solymi.exception;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@JsonComponent
public class ApiExceptionSerializer extends JsonSerializer<ApiException> {

    @Override
    public void serialize(ApiException e, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("message", e.getReason());
        jsonGenerator.writeArrayFieldStart("details");

        for (Object arg : e.getMessageDetailArguments()) {
            serializerProvider.defaultSerializeValue(arg, jsonGenerator);
        }

        jsonGenerator.writeEndArray();
        jsonGenerator.writeObjectFieldStart("error");
        jsonGenerator.writeNumberField("statusCode", e.getStatusCode().value());

        if (e.getStatusCode() instanceof HttpStatus s) {
            jsonGenerator.writeStringField("statusPhrase", s.getReasonPhrase());
        }

        jsonGenerator.writeEndObject();
    }
}
