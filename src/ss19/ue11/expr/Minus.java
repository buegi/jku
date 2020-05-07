package ss19.ue11.expr;

import expr.visitor.ExprVisitor;

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
