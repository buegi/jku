package swe2.ss19.ue11.expr.visitor;

import swe2.ss19.ue11.expr.Add;
import swe2.ss19.ue11.expr.Lit;
import swe2.ss19.ue11.expr.Minus;
import swe2.ss19.ue11.expr.Mult;
import swe2.ss19.ue11.expr.Recip;
import swe2.ss19.ue11.expr.Var;

public class EvalVisitor implements ExprVisitor<Double> {

	@Override
	public Double visit(Lit litExpr) {
		return litExpr.getVal();
	}

	@Override
	public Double visit(Var varExpr) {
		return varExpr.getValue();
	}

	@Override
	public Double visit(Add addExpr) {
		return addExpr.getLeft().accept(this) + addExpr.getRight().accept(this);
	}

	@Override
	public Double visit(Mult multExpr) {
		return multExpr.getLeft().accept(this) * multExpr.getRight().accept(this);
	}

	@Override
	public Double visit(Minus minusExpr) {
		return -minusExpr.getSubExpr().accept(this);
	}

	@Override
	public Double visit(Recip recipExpr) {
		return 1/recipExpr.getSubExpr().accept(this);
	}
	
}
