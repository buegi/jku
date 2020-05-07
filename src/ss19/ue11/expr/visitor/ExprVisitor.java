package ss19.ue11.expr.visitor;

import ss19.ue11.expr.Add;
import ss19.ue11.expr.Lit;
import ss19.ue11.expr.Minus;
import ss19.ue11.expr.Mult;
import ss19.ue11.expr.Recip;
import ss19.ue11.expr.Var;

/**
 * Visitor interface for visiting expressions. 
 * Visit methods have return values which are generic. 
 * 
 * @param <T> the generic return type for visit methods 
 */
public interface ExprVisitor<T> {
	
	// TODO: visitor methods 
	public T visit(Lit litExpr);
	public T visit(Var varExpr);
	public T visit(Add addExpr);
	public T visit(Mult multExpr);
	public T visit(Minus minusExpr);
	public T visit(Recip recipExpr);
	
}
