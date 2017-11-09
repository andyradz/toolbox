package com.codigo.aplios.toolbox.core.domain;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.mappings.DirectToFieldMapping;
import org.eclipse.persistence.mappings.converters.ObjectTypeConverter;

import com.codigo.aplios.toolbox.core.domain.evidence.gender.Gender;

public class MyCustomizer implements DescriptorCustomizer {
	@Override
	public void customize(ClassDescriptor descriptor) {

		DirectToFieldMapping genderMapping = (DirectToFieldMapping) descriptor.getMappingForAttributeName("gender");
		ObjectTypeConverter converter = new ObjectTypeConverter();
		converter.addConversionValue("M", Gender.MALE);
		converter.addConversionValue("F", Gender.FEMALE);
		genderMapping.setConverter(converter);
	}
}
