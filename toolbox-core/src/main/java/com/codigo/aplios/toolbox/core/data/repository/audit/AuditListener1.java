package com.codigo.aplios.toolbox.core.data.repository.audit;

import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.persistence.config.DescriptorCustomizer;
import org.eclipse.persistence.config.SessionCustomizer;
import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.DescriptorEvent;
import org.eclipse.persistence.descriptors.DescriptorEventAdapter;
import org.eclipse.persistence.internal.sessions.DirectToFieldChangeRecord;
import org.eclipse.persistence.queries.InsertObjectQuery;
import org.eclipse.persistence.queries.WriteObjectQuery;
import org.eclipse.persistence.sessions.Session;
import org.eclipse.persistence.sessions.changesets.ChangeRecord;

import com.codigo.aplios.toolbox.core.domain.audit.AuditEntry;
import com.codigo.aplios.toolbox.core.domain.audit.AuditField;

public class AuditListener1 extends DescriptorEventAdapter implements SessionCustomizer, DescriptorCustomizer {
	public static ThreadLocal currentUser = new ThreadLocal();

	/** This will audit a specific class. */
	@Override
	public void customize(ClassDescriptor descriptor) {

		descriptor.getEventManager()
			.addListener(this);
	}

	/** This will audit all classes. */
	@Override
	public void customize(Session session) {

		for (ClassDescriptor descriptor : session.getDescriptors()
			.values())
			this.customize(descriptor);
	}

	@Override
	public void aboutToInsert(DescriptorEvent event) {

		this.processEvent(event, AuditOperation.CREATE_OPERATION);
	}

	@Override
	public void aboutToUpdate(DescriptorEvent event) {

		this.processEvent(event, AuditOperation.UPDATE_OPERATION);
	}

	@Override
	public void aboutToDelete(DescriptorEvent event) {

		this.processEvent(event, AuditOperation.DELETE_OPERATION);
	}

	@SuppressWarnings("unchecked")
	public void processEvent(DescriptorEvent event, AuditOperation operation) {

		Calendar calendar = Calendar.getInstance();
		for (String table : (List<String>) event.getDescriptor()
			.getTableNames()) {
			// event.getRecord().put(table + "." + "CREATED_BY", AuditListener1.currentUser.get());
			event.getRecord()
				.put(table + "." + "AUDIT_USER",
						event.getSession()
							.getDatasourceLogin()
							.getUserName());
			event.getRecord()
				.put(table + "." + "CREATED_DATE", calendar);
			if (operation == AuditOperation.UPDATE_OPERATION)
				this.processWriteEvent(event, operation, calendar, table);
			else
				this.processAuditEvent(event, operation, calendar, table);
		}
		calendar = null;
	}

	protected void processAuditEvent(DescriptorEvent event, AuditOperation operation, Calendar calendar,
			String tableName) {

		AuditEntry entry = this.createAuditEntry(event, operation, calendar, tableName);
		InsertObjectQuery insertQuery = new InsertObjectQuery(entry);
		event.getSession()
			.executeQuery(insertQuery);
	}

	protected void processWriteEvent(DescriptorEvent event, AuditOperation operation, Calendar calendar,
			String tableName) {

		AuditEntry entry = this.createAuditEntry(event, operation, calendar, tableName);

		Collection<AuditField> fields = new LinkedList<>();
		WriteObjectQuery query = (WriteObjectQuery) event.getQuery();
		List<ChangeRecord> changes = query.getObjectChangeSet()
			.getChanges();

		for (ChangeRecord change : changes)
			if (change instanceof DirectToFieldChangeRecord) {
				DirectToFieldChangeRecord fieldChange = (DirectToFieldChangeRecord) change;
				AuditField field = new AuditField();
				field.setAuditEntry(entry);
				field.setFieldName(fieldChange.getAttribute());
				field.setFieldValue(fieldChange.getNewValue()
					.toString());
				fields.add(field);
			}

		entry.setFields(fields);

		InsertObjectQuery insertQuery = new InsertObjectQuery(entry);
		event.getSession()
			.executeQuery(insertQuery);

		for (AuditField field : fields) {
			insertQuery = new InsertObjectQuery(field);
			event.getSession()
				.executeQuery(insertQuery);
		}
	}

	protected AuditEntry createAuditEntry(DescriptorEvent event, AuditOperation operation, Calendar calendar,
			String tableName) {

		AuditEntry entry = new AuditEntry();
		// entry.setAuditUser((String) AuditListener1.currentUser.get());
		entry.setAuditUser(event.getRecord()
			.get("AUDIT_USER")
			.toString());
		// entry.setOperation(operation);
		entry.setOperationTime(calendar);
		entry.setEventId(Long.valueOf(event.getSource()
			.hashCode()));
		entry.setTableName(tableName);
		return entry;
	}

}