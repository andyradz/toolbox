package com.codigo.aplios.toolbox.core.identity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Klasa realizuje mechanizm validacji numeru Pesel. Implementuje domyślny model validacji standardu JavaBeans.
 *
 * @author Andrzej radziszewski
 * @param <PeselIdentity>
 * @since 1.0
 * @category Validator
 */
public class PeselValidator implements ConstraintValidator<PeselCheck, PeselIdentity> {

	private final byte[] peselIdentity = new byte[11];

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * Podstawowy konstruktor obiektu.
	 *
	 * @param peselIdentity numer identyfikacyjny regon.
	 * @throws Exception powstaje ry nieprawidłowy numer identyfikacji regon.
	 */
	public PeselValidator(PeselIdentity peselIdentity) throws IllegalArgumentException {

		Objects.requireNonNull(peselIdentity);

		if (peselIdentity.pesel()
			.length() != 11)
			throw new IllegalArgumentException();

		int arrIndex = 0;
		for (String item : peselIdentity.pesel()
			.split(""))
			this.peselIdentity[arrIndex++] = Byte.parseByte(item);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public void initialize(PeselCheck constraintAnnotation) {

		Map<String, Object> elements = new HashMap<>();
		elements.put("message", constraintAnnotation.message());
		elements.put("payload", constraintAnnotation.payload());
		elements.put("groups", constraintAnnotation.groups());

		// AnnotationDescriptor<Length> descriptor = AnnotationDescriptor.getInstance(Length.class,
		// elements);
		// Length length = AnnotationFactory.create(descriptor);
	}

	// -----------------------------------------------------------------------------------------------------------------

	@Override
	public boolean isValid(PeselIdentity value, ConstraintValidatorContext context) {

		int checksum = (+1 * this.peselIdentity[0]) + (3 * this.peselIdentity[1]) + (7 * this.peselIdentity[2])
				+ (9 * this.peselIdentity[3]) + (1 * this.peselIdentity[4]) + (3 * this.peselIdentity[5])
				+ (7 * this.peselIdentity[6]) + (9 * this.peselIdentity[7]) + (1 * this.peselIdentity[8])
				+ (3 * this.peselIdentity[9]);

		checksum %= 10;
		checksum = 10 - checksum;
		checksum %= 10;
		return (this.peselIdentity[10] == checksum);
	}

	// -----------------------------------------------------------------------------------------------------------------
}
