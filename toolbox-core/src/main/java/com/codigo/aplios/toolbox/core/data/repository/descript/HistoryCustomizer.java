package com.codigo.aplios.toolbox.core.data.repository.descript;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.history.HistoryPolicy;

public class HistoryCustomizer implements DescriptorCustomizer {

	@Override
	public void customize(ClassDescriptor descriptor) {

		HistoryPolicy policy = new HistoryPolicy();
		policy.addHistoryTableName("EMPLOYEE_HIST");
		policy.addStartFieldName("START_DATE");
		policy.addEndFieldName("END_DATE");
		descriptor.setHistoryPolicy(policy);
	}
}