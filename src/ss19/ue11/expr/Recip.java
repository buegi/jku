package ss19.ue11.expr;

import expr.visitor.ExprVisitor;

/**
 * Class for reciprocal unary expressions. 
 */
public class Recip extends UnExpr {

	/**
	 * Constrctor setting the subexpression of this reciprocal expression. 
	 * @param expr the subexpression
	 */
	Recip(Expr expr) {
		super(expr);
	}

	@Override
	public <T> T accept(ExprVisitor<T> visitor) {
		return visitor.visit(this);
	}

}
