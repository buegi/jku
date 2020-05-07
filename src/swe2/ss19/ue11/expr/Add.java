package swe2.ss19.ue11.expr;

import swe2.ss19.ue11.expr.visitor.ExprVisitor;

/**
 * Class for add expressions. 
 */
public class Add extends BinExpr {

	/**
	 * Constructor for the add expression with left and right subexpressions. 
	 * @param left the left subexpression
	 * @param right the right subexpression
	 */
	Add(Expr left, Expr right) {
		super(left, right);
	}

	@Override
	public <T> T accept(ExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
