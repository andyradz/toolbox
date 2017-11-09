package com.codigo.aplios.toolbox.core.pattern;

import java.util.ArrayList;
import java.util.Stack;

// Wzorzec Interpreter na przykładzie ONP | .braindamage
interface Expression {

	public void interpret(Stack<Integer> s);
}

class TerminalExpression_Number implements Expression {

	private int number;

	public TerminalExpression_Number(int number) {

		this.number = number;
	}

	@Override
	public void interpret(Stack<Integer> s) {

		s.push(this.number);
	}
}

class TerminalExpression_Plus implements Expression {

	@Override
	public void interpret(Stack<Integer> s) {

		s.push(s.pop() + s.pop());
	}
}

class TerminalExpression_Minus implements Expression {

	@Override
	public void interpret(Stack<Integer> s) {

		s.push(-s.pop() + s.pop());
	}
}

class Parser {

	private ArrayList<Expression> parseTree = new ArrayList<>(); // only one NonTerminal
	// Expression here

	public Parser(String s) {

		for (String token : s.split(" "))
			if (token.equals("+"))
				this.parseTree.add(new TerminalExpression_Plus());
			else if (token.equals("-"))
				this.parseTree.add(new TerminalExpression_Minus());
			else
				this.parseTree.add(new TerminalExpression_Number(Integer.parseInt(token)));
	}

	public int evaluate() {

		Stack<Integer> context = new Stack<>();
		for (Expression e : this.parseTree)
			e.interpret(context);
		return context.pop();
	}
}

public class InterpreterExample {

	public static void main(String[] args) {

		String expression = "42 4 2 - +";
		Parser p = new Parser(expression);
		System.out.println("'" + expression + "' equals " + p.evaluate());

	}
}
