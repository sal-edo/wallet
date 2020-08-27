package com.saledo.wallet.config;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.saledo.wallet.exception.UnsupportedNumberFormatException;

import lombok.extern.log4j.Log4j2;

/**
 * Override of Jacskon deserializer for BigDecimal format. Number can be entered as a
 * string and every entry will be rounded on two decimal places. In case of error custom
 * UnsupportedNumberFormatException will be thrown.
 */
@Log4j2
@JsonComponent
public class BigDecimalDeserializer extends JsonDeserializer<BigDecimal> {

	private static final String BIGDECIMAL_DESERIALIZE_ERR = "Error while parsing number value {} received.";

	@Override
	public BigDecimal deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		try {

			BigDecimal ret = (new BigDecimal(jp.getValueAsString())).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			return ret.abs();
		}
		catch (Exception ex) {
			log.error(BIGDECIMAL_DESERIALIZE_ERR, jp.getValueAsString(), ex);
			throw new UnsupportedNumberFormatException(jp.getValueAsString());
		}
	}
}
