package com.codigo.aplios.toolbox.core.generators;

//// Kodowanie Lambda
//// https://thecannycoder.wordpress.com/
//
//// Dziel i zwyciezaj
//// https://thecannycoder.wordpress.com/
// public class Generator {
//
// private static class SquareNums implements IntSupplier {
// private int i = 0;
//
// @Override
// public int getAsInt() {
//
// this.i++;
// return (i * i);
// }
// }
//
// public static void main(String[] args) {
// // SquareNums gen = new SquareNums();
// // IntStream stream = IntStream.generate(gen);
// // stream.limit(100L).forEach(System.out::println);
//
// Function<Integer, Integer> transf = (val) -> val * 2;
// Function<Integer, Integer> transt = (val) -> val * val;
// System.out.println(transf.compose(transt).apply(4));
//
// System.out.println(transf.andThen(transt).apply(4));
//
// Supplier<Double> PI = () -> 3.14;
//
// System.out.println(PI.get());
// System.out.println(Function.identity().apply("2323"));
// }
// }
//
// interface PermutationA {
// default double getResult() {
//
// return 1.0;
// }
// }
//
// interface PermutationB {
// default double getResult() {
//
// return 2.0;
// }
// }
//
// final class Permuatation implements PermutationA, PermutationB {
// @Override
// public double getResult() {
//
// return PermutationA.super.getResult();
// }
// }
//
// @FunctionalInterface
// interface Command<T, V> {
// V execute(T parameter);
//
// default void itMayContainsDefaultMethod() {
//
// }
//
// default Long andAnotherDefaultMethodAndStillBeAFunctionalInterface() {
//
// return 20L;
// }
// }
//
// class PersonTools {
// public static void serveAlcohol(Person person, Predicate<Person> isAdult) {
//
// if (isAdult.test(person))
// System.out.println("Serving alcohol");
// }
//
// public static void foo() {
//
// // serveAlcohol(adult(), p(Person::isChild).negate());
// }
//
// public static <T> Predicate<T> p(Predicate<T> p) {
//
// return p;
// }
// }
//
// interface Person {
// boolean isChild();
//
// static Person child() {
//
// return () -> true;
// }
//
// static Person adult() {
//
// return () -> false;
// }
// }
