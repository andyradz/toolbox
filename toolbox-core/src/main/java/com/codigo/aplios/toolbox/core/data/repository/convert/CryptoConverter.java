package com.codigo.aplios.toolbox.core.data.repository.convert;

import java.util.Base64;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

	@Override
	public String convertToDatabaseColumn(String ccNumber) {

		try {
			byte[] encodedBytes = new byte[1024];
			encodedBytes = Base64.getDecoder()
				.decode(ccNumber.getBytes("UTF-8"));

			return new String(encodedBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {

		try {

			byte[] encodedBytes = Base64.getEncoder()
				.encode(dbData.getBytes("UTF-8"));
			return new String(encodedBytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
