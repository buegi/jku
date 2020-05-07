package swe2.ss19.ue11.expr.visitor;

import swe2.ss19.ue11.expr.Add;
import swe2.ss19.ue11.expr.Expr;
import swe2.ss19.ue11.expr.Exprs;
import swe2.ss19.ue11.expr.Lit;
import swe2.ss19.ue11.expr.Minus;
import swe2.ss19.ue11.expr.Mult;
import swe2.ss19.ue11.expr.Recip;
import swe2.ss19.ue11.expr.Var;

public class SimplyVisitor implements ExprVisitor<Expr> {

	@Override
	public Expr visit(Lit litExpr) {
		return litExpr;
	}

	@Override
	public Expr visit(Var varExpr) {
		return varExpr;
	}

	@Override
	public Expr visit(Add addExpr) {
		
		Expr leftLit = addExpr.getLeft().accept(this);
		Expr rightLit = addExpr.getRight().accept(this);
		double valueLeft;
		double valueRight;
		
		if (leftLit instanceof Lit) {
			valueLeft = ((Lit) leftLit).getVal();
			if (valueLeft == 0.0) {
				return rightLit;
			}
		}
		
		if (rightLit instanceof Lit) {
			valueRight = ((Lit) rightLit).getVal();
			if (valueRight == 0.0) {
				return leftLit;
			}
		}
		
		return Exprs.add(leftLit, rightLit);
	}

	@Override
	public Expr visit(Mult multExpr) {
		Expr leftLit = multExpr.getLeft().accept(this);
		Expr rightLit = multExpr.getRight().accept(this);
		double valueLeft;
		double valueRight;
		
		if (leftLit instanceof Lit) {
			valueLeft = ((Lit) leftLit).getVal();
			if (valueLeft == 1.0) {
				return rightLit;
			}
			
			if (valueLeft == 0.0) {
				return leftLit;
			}
		}
		
		if (rightLit instanceof Lit) {
			valueRight = ((Lit) rightLit).getVal();
			if (valueRight == 1.0) {
				return leftLit;
			}
			
			if (valueRight == 0.0) {
				return rightLit;
			}
		}
		
		return Exprs.mult(leftLit, rightLit);
	}

	@Override
	public Expr visit(Minus minusExpr) {
		
		Expr thisExpr = minusExpr.getSubExpr().accept(this);
		
		if (thisExpr instanceof Minus) {
			return ((Minus) thisExpr).getSubExpr();
		}
		
		return Exprs.minus(thisExpr);
	}

	@Override
	public Expr visit(Recip recipExpr) {
		
		Expr thisExpr = recipExpr.getSubExpr().accept(this);
		
		if (thisExpr instanceof Recip) {
			return ((Recip) thisExpr).getSubExpr();
		}
		
		return Exprs.recip(thisExpr);
	}

}
