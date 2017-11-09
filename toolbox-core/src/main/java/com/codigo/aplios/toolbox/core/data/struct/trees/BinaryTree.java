package com.codigo.aplios.toolbox.core.data.struct.trees;

// https://sourcemaking.com/design_patterns/composite

// kompozyt
// http://lukaszkosiorowski.pl/programowanie/kompozyt-composite/

// http://kodatnik.blogspot.com/2010/02/drzewo-ogolna-implementacja.html

// budowa bazy danych od środka
// http://edu.pjwstk.edu.pl/wyklady/sbd/scb/w1.htm

interface Node {

}

class Leafnode<T> implements Node {

}

class parentNode<T> implements Node {

}

public class BinaryTree {

}

// interface NodeVisitor {
// void beforeVisitingLeftChild(node * pNode);
//
// void beforeVisitingRightChild(node * pNode);
//
// void afterVisitingLeftChild(node * pNode);
//
// void afterVisitingRightChild(node * pNode);
//
// void visit(node * pNode) = 0;
// };
//
// class Traversable {
// NodeVisitor mPNodeVisitor;
//
// public BTTraversal()
// {
// mPNodeVisitor = new DisplayVisitor();
// }
//
// void traverse(node * pNode, NodeVisitor * pNodeVisitor) = 0;
//
// void traverse(node * pNode)
// {
// traverse(pNode, mPNodeVisitor);
// }
// };
// http://thispointer.com/binary-tree-traversal-with-strategy-design-pattern-and-open-closed-principle/