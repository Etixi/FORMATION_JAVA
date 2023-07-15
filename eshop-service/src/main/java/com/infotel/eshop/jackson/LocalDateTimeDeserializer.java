package com.infotel.eshop.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


public class LocalDateTimeDeserializer extends StdDeserializer<LocalDateTime> {


	protected LocalDateTimeDeserializer() {
		super(LocalDateTime.class);
	}

	@Override
	public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		String value = p.getValueAsString();
		return LocalDateTime.parse(value);
	}

	
	
}
