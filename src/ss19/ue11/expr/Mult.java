package ss19.ue11.expr;

import ss19.ue11.expr.visitor.ExprVisitor;

/**
 * Class for multiplication expressions. 
 */
public class Mult extends BinExpr {
	
	/**
	 * Constructor for the multiplication expression with left and right subexpressions. 
	 * @param left the left subexpression
	 * @param right the right subexpression
	 */
	Mult(Expr left, Expr right) {
		super(left, right);
	}

	@Override
	public <T> T accept(ExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
