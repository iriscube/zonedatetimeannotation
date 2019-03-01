package com.github.iriscube;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import java.time.ZonedDateTime;
import org.jsonschema2pojo.AbstractAnnotator;

public class JacksonConfig extends AbstractAnnotator {
    public JacksonConfig() {
    }

    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);
        if (field.type().erasure().equals(field.type().owner().ref(ZonedDateTime.class))) {
            field.annotate(JsonDeserialize.class).param("using", JsonZonedDateTimeJackson.ZonedDateTimeDeserializer.class);
            field.annotate(JsonSerialize.class).param("using", JsonZonedDateTimeJackson.ZonedDateTimeSerializer.class);
        }

    }
}