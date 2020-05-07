package ss19.ue11.expr.app;

import static ss19.ue11.expr.Exprs.add;
import static ss19.ue11.expr.Exprs.lit;
import static ss19.ue11.expr.Exprs.minus;
import static ss19.ue11.expr.Exprs.mult;
import static ss19.ue11.expr.Exprs.recip;
import static ss19.ue11.expr.Exprs.var;
import static inout.In.readChar;
import static inout.Out.print;
import static inout.Out.println;

import ss19.ue11.expr.Add;
import ss19.ue11.expr.Expr;
import ss19.ue11.expr.Lit;
import ss19.ue11.expr.Minus;
import ss19.ue11.expr.Mult;
import ss19.ue11.expr.Recip;
import ss19.ue11.expr.Var;
import ss19.ue11.expr.visitor.EvalVisitor;
import ss19.ue11.expr.visitor.InfixReprVisitor;
import ss19.ue11.expr.visitor.SimplyVisitor;
import inout.In; 

/**
 * Class implementing a simple dialog for working with expressions. 
 * The user can input command keys and additional data. 
 * The application creates expressions and stores them in an expression array 
 * for reuse in other operations. 
 * 
 * Operations are 
 * <ul>
 * <li> l : for new literal </li>
 * <li> v : for new variable </li>
 * <li> = : for set variable value </li>
 * <li> + : for new add expression </li>
 * <li> * : for new mult expression </li>
 * <li> - : for new minus expression </li>
 * <li> / : for new reciprocal expression </li>
 * <li> e : for eval expression </li>
 * <li> s : for simplify expression </li>
 * <li> x : for exit program </li>
 * </ul>
 * 
 * The program uses visitors 
 * <ul>
 * <li>for creating string representation of expressions</li>
 * <li>for evaluating expressions</li>
 * <li>for simplifying expressions</li>
 * </ul>
 */
public class ExprApp {
	
	private final static Expr[] exprs = new Expr[1000]; 
	private static int nExpr = 0; 

	/**
	 * Main method 
	 * @param args not used 
	 */
	public static void main(String[] args) {
		printCmds(); 
		
		int i, i1, i2; 
		String exprStrg = ""; 
		char cmd = ' '; 
		do {
			cmd = readCmd(); 
			switch (cmd) {
			case 'l': 
				double value = readValue(); 
				Lit l = lit(value); 
				i = storeExpr(l); 
				// TODO: create infix representation  
				exprStrg = l.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case 'v': 
				String name = readName(); 
				value = readValue(); 
				Var v = var(name, value); 
				i = storeExpr(v); 
				// TODO: create infix representation 
				exprStrg = v.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case '=': 
				i = readIdx(); 
				Expr e = exprs[i]; 
				if (e instanceof Var) {
					value = readValue(); 
					((Var) e).setValue(value);
					// TODO: create infix representation  
					exprStrg = e.accept(new InfixReprVisitor());
					printExpr(i, exprStrg); 
				} else {
					println("  Not a variable at position " + i); 
				}
				break; 
			case '+': 
				i1 = readIdx(); 
				i2 = readIdx(); 
				Add a = add(exprs[i1], exprs[i2]); 
				i = storeExpr(a); 
				// TODO: create infix representation  
				exprStrg = a.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case '*': 
				i1 = readIdx(); 
				i2 = readIdx(); 
				Mult m = mult(exprs[i1], exprs[i2]); 
				i = storeExpr(m); 
				// TODO: create infix representation  
				exprStrg = m.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case '-': 
				i = readIdx(); 
				Minus mi = minus(exprs[i]); 
				i = storeExpr(mi); 
				// TODO: create infix representation  
				exprStrg = mi.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case '/': 
				i = readIdx(); 
				Recip r = recip(exprs[i]); 
				i = storeExpr(r); 
				// TODO: create infix representation  
				exprStrg = r.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case 'e': 
				i = readIdx(); 
				// TODO: evaluate expression exprs[i]
				value = exprs[i].accept(new EvalVisitor());
				Lit vl = lit(value); 
				i = storeExpr(vl); 
				// TODO: create infix representation 
				exprStrg = vl.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case 's': 
				i = readIdx(); 
				// TODO: simplify expression exprs[i]
				Expr se = exprs[i].accept(new SimplyVisitor());
				i = storeExpr(se); 
				// TODO: create infix representation  
				exprStrg = se.accept(new InfixReprVisitor());
				printExpr(i, exprStrg); 
				break; 
			case 'x': 
				println("Thank you for using Expressions");
			}
		} while (cmd != 'x'); 
		
		
	}
	
	/**
	 * Prints the available commands. 
	 */
	private static void printCmds() {
		println("Expression commands:"); 
		println("=========================="); 
		println("l : new literal "); 
		println("v : new variable "); 
		println("= : set variable value "); 
		println("+ : new add expression "); 
		println("* : new mult expression "); 
		println("- : new minus expression "); 
		println("/ : new reciprocal expression "); 
		println("e : eval expression "); 
		println("s : simplify expression "); 
		println("x : exit program "); 
		println("--------------------------"); 
	}
	
	/**
	 * Prints the expression string from index i 
	 * @param i the index of the expression
	 */
	private static void printExpr(int i, String exprStr) {
		printf("[%d] %s", i, exprStr); 
		
	}
	
	/** Formatted prints using {@link inout.Out#println(String)} 
	 * @param fmt the format string
	 * @param vals the values used 
	 */
	private static void printf(String fmt, Object...vals) {
		inout.Out.println(String.format(fmt, vals)); 
	}

	/** Reads a word for a variable name. 
	 * @return the variable name 
	 */
	private static String readName() {
		print("    Variable name: ");
		return In.readWord();
	}

	/**
	 * Reads a double value. 
	 * @return the double value read
	 */
	private static double readValue() {
		print("    Double value: ");
		return In.readDouble();
	}

	/**
	 * Reads an index for an expression.
	 * Index must be withing 0 and number of expressions (exclusive) 
	 * @return the index for an expression
	 */
	private static int readIdx() {
		int idx = -1; 
		do {
			print("    Index: ");
			idx = In.readInt();
			if (idx < 0 || idx >= nExpr) {
				printf("   Invalid index! Select within [0..%d)", nExpr);
				idx = -1; 
			} 
		} while (idx < 0); 
		return idx; 
	}

	/**
	 * Reads a command character. 
	 * Command characters have to be one of x, l, v, =, +, *, -, /, e, and s
	 * @return the command character
	 */
	private static char readCmd() {
		String cmds = "xlv=+*-/es";
		char cmd = ' ';
		do {
			print("  Command: ");
			cmd = readChar(); 
			if (cmds.indexOf(cmd) < 0) {
				println("Invalid command!"); 
			}
		} while (cmds.indexOf(cmd) < 0);
		return cmd;
	}

	/** Stores an expression at the next free position.
	 * @param e the expression
	 * @return the position the expression is stored 
	 */
	private static int storeExpr(Expr e) {
		exprs[nExpr] = e; 
		int p = nExpr; 
		nExpr = (nExpr + 1) % 1000; 
		return p; 
	}

}
