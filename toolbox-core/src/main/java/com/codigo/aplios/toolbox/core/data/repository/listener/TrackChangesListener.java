package com.codigo.aplios.toolbox.core.data.repository.listener;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface TrackChanges {
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ChangeLog {

}
//
// public class TrackChangesListener {
// @PostLoad
// public void loadOldData(Object he) {
//
// if (!Editor.isEdited(he)) {
// return;
// }
//
// Map oldData = new HashMap();
//
// collectOldValues(oldData, "", he);
//
// Editor.setOldData(he, oldData);
// }
//
// private void collectOldValues(Map oldData, String prefix, Object he) {
//
// List historisedFields = getHistorisedFields(he);
//
// for (Field field : historisedFields) {
// field.setAccessible(true);
// try {
// if (field.getAnnotation(Embedded.class) != null) {
// Object value = field.get(he);
// if (value != null) {
// // recurse into the Embedded field
// collectOldValues(oldData, prefix + field.getName() + ".", value);
// }
// } else {
// oldData.put(prefix + field.getName(), field.get(he));
// }
// } catch (IllegalAccessException e) {
// throw new RuntimeException(e);
// }
// }
// }
//
// @PreUpdate
// public void updateChangeLog(Object he) {
//
// if (!Editor.isEdited(he)) {
// return;
// }
//
// try {
// Field changeLogField = getChangeLogField(he);
// Validate.notNull(changeLogField, "could not find @ChangeLog field in Class " +
// he.getClass().getName());
//
// StringBuilder changeLog = new StringBuilder();
//
// String changeLogValue = (String) changeLogField.get(he);
//
// if (changeLogValue != null) {
// changeLog.append(changeLogValue).append("\n\n");
// }
//
// changeLog.append("changedBy ").append(Editor.getUser());
// changeLog.append(" on ").append((new Date()).toGMTString()).append('\n');
//
// boolean changed = false;
//
// changed = collectChanges(changeLog, Editor.getOldData(he), "", he);
//
// // update the changeLog field if a trackable change was detected
// if (changed) {
// changeLogField.set(he, changeLog.toString());
// }
// } catch (IllegalAccessException e) {
// throw new RuntimeException(e);
// }
// }
//
// private Field getChangeLogField(Object he) {
//
// Field[] fields = he.getClass().getDeclaredFields();
//
// for (Field field : fields) {
// if (field.getAnnotation(ChangeLog.class) != null) {
// field.setAccessible(true);
// return field;
// }
// }
// return null;
// }
//
// private boolean collectChanges(StringBuilder changeLog, Map oldData, String prefix, Object he) {
//
// boolean changed = false;
// List historisedFields = getHistorisedFields(he);
// for (Field field : historisedFields) {
// field.setAccessible(true);
// String fieldName = field.getName();
// try {
// if (field.getAnnotation(Embedded.class) != null) {
// Object value = field.get(he);
// if (value != null) {
// // recurse into the Embedded field
// changed |= collectChanges(changeLog, oldData, prefix + field.getName() + ".", value);
// }
// } else {
// Object newValue = field.get(he);
// Object oldValue = oldData.get(prefix + fieldName);
// changed |= addChangeLine(changeLog, prefix + fieldName, oldValue, newValue);
// }
// } catch (IllegalAccessException e) {
// throw new RuntimeException(e);
// }
// }
// return changed;
// }
//
// private boolean addChangeLine(StringBuilder changeLog, String fieldName, Object oldValue, Object
// newValue) {
//
// if (oldValue == null && newValue == null) {
// return false;
// }
// if (oldValue != null && oldValue.equals(newValue)) {
// // no change
// return false;
// }
//
// String changeLine =
// fieldName + " old: [" + (oldValue != null ? oldValue : "") + "] new: [" + (newValue != null
// ? newValue : "") + "]\n";
// changeLog.append(changeLine);
// return true;
// }
//
// private List getHistorisedFields(Object he) {
//
// Field[] fields = he.getClass().getDeclaredFields();
// List histFields = new ArrayList();
//
// for (Field field : fields) {
// if (field.getAnnotation(TrackChanges.class) != null) {
// histFields.add(field);
// }
// }
//
// return histFields;
// }
//
// }
