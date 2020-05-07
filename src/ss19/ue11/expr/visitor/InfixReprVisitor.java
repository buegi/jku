package ss19.ue11.expr.visitor;

import ss19.ue11.expr.Add;
import ss19.ue11.expr.Lit;
import ss19.ue11.expr.Minus;
import ss19.ue11.expr.Mult;
import ss19.ue11.expr.Recip;
import ss19.ue11.expr.Var;

public class InfixReprVisitor implements ExprVisitor<String> {

	@Override
	public String visit(Lit litExpr) {
		return String.valueOf(litExpr.getVal());
	}

	@Override
	public String visit(Var varExpr) {
		return varExpr.getName();
	}

	@Override
	public String visit(Add addExpr) {
		return "(" + addExpr.getLeft().accept(this) + " + " + addExpr.getRight().accept(this) + ")";
	}

	@Override
	public String visit(Mult multExpr) {
		return "(" + multExpr.getLeft().accept(this) + " * " + multExpr.getRight().accept(this) + ")";
	}

	@Override
	public String visit(Minus minusExpr) {
		return "(-" + minusExpr.getSubExpr().accept(this) + ")";
	}

	@Override
	public String visit(Recip recipExpr) {
		return "(1/" + recipExpr.getSubExpr().accept(this) + ")";
	}

}
