package com.codigo.aplios.toolbox.core.data.struct.trees;

/// **
// * Klasa Node<T> - węzeł drzewa
// *
// * @author kodatnik.blogspot.com
// */
// public class Node<T> {
// private T data;
// private Node<T> parent;
// private LinkedList<Node<T>> children;
//
// public Node() {
// parent = null;
// children = new LinkedList<Node<T>>();
// }
//
// public Node(Node<T> parent) {
// this();
// this.parent = parent;
// }
//
// public Node(Node<T> parent, T data) {
// this(parent);
// this.data = data;
// }
//
// public Node<T> getParent() {
//
// return parent;
// }
//
// public void setParent(Node<T> parent) {
//
// this.parent = parent;
// }
//
// public T getData() {
//
// return data;
// }
//
// public void setData(T data) {
//
// this.data = data;
// }
//
// public int getDegree() {
//
// return children.size();
// }
//
// public boolean isLeaf() {
//
// return children.isEmpty();
// }
//
// public Node<T> addChild(Node<T> child) {
//
// child.setParent(this);
// children.add(child);
// return child;
// }
//
// public Node<T> addChild(T data) {
//
// Node<T> child = new Node<T>(this, data);
// children.add(child);
// return child;
// }
//
// public Node<T> getChild(int i) {
//
// return children.get(i);
// }
//
// public Node<T> removeChild(int i) {
//
// return children.remove(i);
// }
//
// public void removeChildren() {
//
// children.clear();
// }
//
// public LinkedList<Node<T>> getChildren() {
//
// return children;
// }
//
// public Node<T> getLeftMostChild() {
//
// if (children.isEmpty())
// return null;
// return children.get(0);
// }
//
// public Node<T> getRightSibling() {
//
// if (parent != null) {
// LinkedList<Node<T>> parentsChildren = parent.getChildren();
// int pos = parentsChildren.indexOf(this);
// if (pos < (parentsChildren.size() - 1))
// return parentsChildren.get(pos + 1);
// }
// return null;
// }
//
// @Override
// public String toString() {
//
// return data.toString();
// }
// }
//
/// **
// * Klasa Tree<T> - drzewo
// *
// * @author kodatnik.blogspot.com
// */
// class Tree<T> {
// // referencja do węzła będącego korzeniem
// private final Node<T> root;
//
// public Tree() {
// // brak korzenia
// root = null;
// }
//
// public Tree(Node<T> root) {
// // ustawiamy korzeń
// this.root = root;
// }
// }
//
// class NaszeDrzewo {
// public static void main(String args[]) {
//
// // tworzymy węzeł będący korzeniem
// Node<String> korzen = new Node<String>(null, "Adam");
//
// // dodajemy do niego kolejne węzły
// Node<String> n1 = korzen.addChild("Ewa");
// Node<String> n2 = korzen.addChild("Jarek");
// Node<String> n3 = korzen.addChild("Marta");
//
// n1.addChild("Jurek");
// n1.addChild("Max");
// n1.addChild("Iza");
//
// n2.addChild("Iwona");
//
// n3.addChild("Rafał");
// n3.addChild("Ola");
//
// // tworzymy drzewo i wskazujemy, który węzeł jest korzeniem
// Tree<String> drzewo = new Tree<String>(korzen);
// }
// }