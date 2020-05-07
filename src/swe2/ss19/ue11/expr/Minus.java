package swe2.ss19.ue11.expr;

import swe2.ss19.ue11.expr.visitor.ExprVisitor;

/**
 * Class for minus unary expressions. 
 */
public class Minus extends UnExpr {

	/**
	 * Constructor setting the subexpression. 
	 * @param expr the subexpression
	 */
	Minus(Expr expr) {
		super(expr);
	}

	@Override
	public <T> T accept(ExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
