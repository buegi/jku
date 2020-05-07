package ss19.ue11.expr;

import expr.visitor.ExprVisitor;

/**
 * Class for literal expressions. 
 * Literals have a constant double value. 
 */
public class Lit implements Expr {
	
	/** the constant double value*/
	private final double val; 

	/**
	 * Constructor setting the constant value. 
	 * @param val the value of this literal
	 */
	Lit(double val) {
		super();
		this.val = val;
	}

	/**
	 * Gets the constant value of this literal. 
	 * @return the constant value 
	 */
	public double getVal() {
		return val;
	}

	@Override
	public <T> T accept(ExprVisitor<T> visitor) {
		return visitor.visit(this);
	}
	
}
