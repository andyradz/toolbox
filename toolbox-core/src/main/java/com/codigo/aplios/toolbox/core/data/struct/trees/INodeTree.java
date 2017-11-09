package com.codigo.aplios.toolbox.core.data.struct.trees;

/// *
// * Dla każdego drzewa można określić: długość drogi u (głębokość) - liczba wierzchołków, przez które należy przejść od
// * korzenia do wierzchołka u wysokość u - maksymalna liczba wierzchołków na drodze od u do pewnego liścia wysokość
// * drzewa = głębokość = wysokość korzenia +1 ścieżka z u do v - zbiór wierzchołków, przez które należy przejść z
// * wierzchołka u do v droga - ścieżka skierowana stopień wierzchołka - liczba jego bezpośrednich następników stopień
// * drzewa - maksymalny stopień wierzchołka
// */
// interface INode<T> {
// public Node<T> getParent(); // zwraca referencje rodzica
//
// public void setParent(Node<T> parent); // ustawia rodzica dla węzła
//
// public T getData(); // zwraca przechowywane dane
//
// public void setData(T data); // ustawia dane w węźle
//
// public int getDegree(); // zwraca stopień węzła
//
// public Node<T> getChild(int i); // zwraca referencje do i-tego dziecka
//
// public boolean isLeaf(); // sprawdza czy węzeł jest liściem
//
// public Node<T> addChild(Node<T> child); // dodaje do węzła dziecko (inny węzeł)
//
// public Node<T> addChild(T data); // tworzy i dodaje do węzła dziecko z danymi
//
// public Node<T> removeChild(int i); // usuwa i-te dziecko węzła
//
// public void removeChildren(); // usuwa wszystkie dzieci węzła
//
// public Node<T> getLeftMostChild(); // zwraca pierwsze dziecko węzła (z lewej)
//
// public LinkedList<Node<T>> getChildren(); // zwraca listę dzieci
//
// public Node<T> getRightSibling(); // zwraca kolejny element siostrzany węzła
//
// @Override
// public String toString(); // wyświetla węzeł (najczęściej dane)
// }
//
// class Node<T> implements INode<T> {
// private T data; // dane
// private Node<T> parent; // referencja do rodzica
// private LinkedList<Node<T>> children; // lista dzieci
//
// public Node() { // konstruktor domyślny
// parent = null;
// children = new LinkedList<Node<T>>();
// }
//
// public Node(Node<T> parent) { // konstruktor jednoparametrowy
// this();
// this.parent = parent;
// }
//
// public Node(Node<T> parent, T data) { // konstruktor dwuparametrowy
// this(parent);
// this.data = data;
// }
//
// @Override
// public Node<T> getParent() {
//
// return parent;
// }
//
// @Override
// public void setParent(Node<T> parent) {
//
// this.parent = parent;
// }
//
// @Override
// public T getData() {
//
// return data;
// }
//
// @Override
// public void setData(T data) {
//
// this.data = data;
// }
//
// @Override
// public int getDegree() {
//
// return children.size();
// }
//
// @Override
// public Node<T> getChild(int i) {
//
// return children.get(i);
// }
//
// @Override
// public boolean isLeaf() {
//
// return children.isEmpty();
// }
//
// @Override
// public Node<T> addChild(Node<T> child) {
//
// child.setParent(this);
// children.add(child);
// return child;
// }
//
// @Override
// public Node<T> addChild(T data) {
//
// Node<T> child = new Node<T>(this, data);
// children.add(child);
// return child;
// }
//
// @Override
// public Node<T> removeChild(int i) {
//
// return children.remove(i);
// }
//
// @Override
// public void removeChildren() {
//
// children.clear();
// }
//
// @Override
// public Node<T> getLeftMostChild() {
//
// if (children.isEmpty())
// return null;
// return children.get(0);
// }
//
// @Override
// public LinkedList<Node<T>> getChildren() {
//
// if (children.isEmpty())
// return null;
// return children;
// }
//
// @Override
// public Node<T> getRightSibling() {
//
// if (parent != null) {
// LinkedList<Node<T>> childrenParent = parent.getChildren();
// int pozycja = childrenParent.indexOf(this);
// if (childrenParent.size() > pozycja + 1)
// return childrenParent.get(pozycja + 1);
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
// class Tree<T> {
// private Node<T> root; // referencja do korzenia
//
// public Tree() { // konstruktor domyślny
// root = null;
// }
//
// public Tree(Node<T> root) { // konstruktor jednoparametrowy
// this.root = root;
// }
//
// public Node<T> getRoot() {
//
// return root;
// }
//
// public void preOrder(Node<T> n) {
//
// System.out.print(n + " ");
// Node<T> temp = n.getLeftMostChild();
// while (temp != null) {
// preOrder(temp);
// temp = temp.getRightSibling();
// }
// }
//
// public void inOrder(Node<T> n) {
//
// if (n.isLeaf())
// System.out.print(n + " ");
// else {
// Node<T> temp = n.getLeftMostChild();
// inOrder(temp);
// System.out.print(n + " ");
// temp = temp.getRightSibling();
// while (temp != null) {
// inOrder(temp);
// temp = temp.getRightSibling();
// }
// }
// }
//
// public void postOrder(Node<T> n) {
//
// Node<T> temp = n.getLeftMostChild();
// while (temp != null) {
// postOrder(temp);
// temp = temp.getRightSibling();
// }
// System.out.print(n + " ");
// }
//
// }
//
// class Drzewo {
// public static void main(String[] args) {
//
// Node<String> korzen = new Node<String>(null, "G");
//
// Node<String> n1 = korzen.addChild("E");
// Node<String> n2 = korzen.addChild("C");
// Node<String> n3 = korzen.addChild("V");
// n1.addChild("Z");
// Node<String> n4 = n1.addChild("M");
// n1.addChild("P");
// n4.addChild("J");
// Node<String> n5 = n2.addChild("X");
// n5.addChild("H");
// n5.addChild("O");
// n3.addChild("B");
// Node<String> n6 = n3.addChild("S");
// n6.addChild("F");
//
// Tree<String> drzewo = new Tree<String>(korzen);
//
// System.out.print("Pre Order: ");
// drzewo.preOrder(korzen);
// System.out.print("\nPost Order: ");
// drzewo.postOrder(korzen);
// System.out.print("\nIn Order: ");
// drzewo.inOrder(korzen);
// System.out.println();
//
// }
// }
