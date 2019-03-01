package com.github.iriscube;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonZonedDateTimeJackson {
    public JsonZonedDateTimeJackson() {
    }

    public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
        public ZonedDateTimeSerializer() {
        }

        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            String zdts = value.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            gen.writeString(zdts);
        }
    }

    public static class ZonedDateTimeDeserializer extends JsonDeserializer<ZonedDateTime> {
        public ZonedDateTimeDeserializer() {
        }

        public ZonedDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return ZonedDateTime.parse(((TextNode)p.readValueAs(TextNode.class)).textValue(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        }
    }
}