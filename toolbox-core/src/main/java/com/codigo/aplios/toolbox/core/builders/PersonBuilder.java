package com.codigo.aplios.toolbox.core.builders;

//// public abstract class PersonBuilder {
////
//// public static void main(String[] args) {
////
//// Director director = new Director();
//// director.setBuilder(new FemalePerson());
//// director.build();
////
//// Person person = director.getPerson();
////
//// director.setBuilder(new MalePerson());
//// director.build();
//// person = director.getPerson();
//// }
////
//// protected PersonBuilder() {
////
//// }
////
//// public Person getPerson() {
////
//// return this.person;
//// }
////
//// protected Person person;
////
//// public abstract void buildName();
////
//// public abstract void buildSurname();
////
//// }
////
//// class Director {
//// private PersonBuilder builder;
////
//// public Person getPerson() {
////
//// return this.builder.getPerson();
//// }
////
//// public void setBuilder(PersonBuilder builder) {
////
//// this.builder = builder;
//// }
////
//// public void build() {
////
//// this.builder.buildSurname();
//// this.builder.buildName();
//// }
////
//// }
////
//// class Person {
//// private String name;
//// private String surname;
////
//// public void setName(String name) {
////
//// this.name = name;
//// }
////
//// public void setSurname(String surname) {
////
//// this.surname = surname;
//// }
//// }
////
//// class FemalePerson extends PersonBuilder {
////
//// public FemalePerson() {
////
//// super();
//// this.person = new Person();
//// }
////
//// @Override
//// public void buildName() {
////
//// this.person.setName("Agnieszka");
////
//// }
////
//// @Override
//// public void buildSurname() {
////
//// this.person.setSurname("Wadziszewska");
////
//// }
////
//// }
////
//// class MalePerson extends PersonBuilder {
////
//// public MalePerson() {
////
//// super();
//// this.person = new Person();
//// }
////
//// @Override
//// public void buildName() {
////
//// this.person.setName("Andrzej");
////
//// }
////
//// @Override
//// public void buildSurname() {
////
//// this.person.setSurname("Radziszewski");
//// }
// }