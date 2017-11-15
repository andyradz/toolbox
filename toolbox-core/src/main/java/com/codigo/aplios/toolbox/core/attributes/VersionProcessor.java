package com.codigo.aplios.toolbox.core.attributes;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * @author Andrzej Radziszewski
 *
 */
@SupportedAnnotationTypes({ "com.codigo.aplios.contos.system.attributes.Version" })
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class VersionProcessor extends AbstractProcessor {

	/**
	 * Podstawowy konstruktor obiektu. Konstruktor wymagany zgodnie ze specyfikacją JavaBeans;
	 */
	public VersionProcessor() {

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set,
	 * javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		//
		// for (TypeElement currAnn : annotations) {
		// Name qualifiedName = currAnn.getQualifiedName();
		// if (qualifiedName.contentEquals("com.codigo.aplios.contos.system.attributes.Version")) {
		// Set<? extends Element> annElems;
		// annElems = roundEnv.getElementsAnnotatedWith(currAnn);
		//
		// for (Element element : annElems) {
		// Version v = element.getAnnotation(Version.class);
		// int major = v.major();
		// int minor = v.minor();
		// if ((major < 0) || (minor < 0)) {
		// String errorMsg = "Version cannot" + "be negative." + " major" + major + " minor" + minor;
		// final Messager messager = this.processingEnv.getMessager();
		// messager.printMessage(Kind.ERROR, errorMsg, element);
		// }
		// }
		// }
		// }
		return false;
	}
}
