package swe2.ss19.ue11.expr;

import swe2.ss19.ue11.expr.visitor.ExprVisitor;

/**
 * Interface for expressions. 
 * Expressions should allow visitors.  
 */
public interface Expr {
	
	public <T> T accept(ExprVisitor<T> visitor);
	
}
