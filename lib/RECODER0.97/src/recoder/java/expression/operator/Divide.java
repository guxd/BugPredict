// This file is part of the RECODER library and protected by the LGPL.

package recoder.java.expression.operator;

import recoder.java.Expression;
import recoder.java.SourceVisitor;
import recoder.java.expression.Operator;

/**
 * Divide.
 * 
 * @author <TT>AutoDoc</TT>
 */

public class Divide extends Operator {

    /**
	 * serialization id
	 */
	private static final long serialVersionUID = -5919215185261848809L;

	/**
     * Divide.
     */

    public Divide() {
        super();
    }

    /**
     * Divide.
     * 
     * @param lhs
     *            an expression.
     * @param rhs
     *            an expression.
     */

    public Divide(Expression lhs, Expression rhs) {
        super(lhs, rhs);
        makeParentRoleValid();
    }

    /**
     * Divide.
     * 
     * @param proto
     *            a divide.
     */

    protected Divide(Divide proto) {
        super(proto);
        makeParentRoleValid();
    }

    /**
     * Deep clone.
     * 
     * @return the object.
     */

    public Divide deepClone() {
        return new Divide(this);
    }

    /**
     * Get arity.
     * 
     * @return the int value.
     */

    public int getArity() {
        return 2;
    }

    /**
     * Get precedence.
     * 
     * @return the int value.
     */

    public int getPrecedence() {
        return 2;
    }

    /**
     * Get notation.
     * 
     * @return the int value.
     */

    public int getNotation() {
        return INFIX;
    }

    public void accept(SourceVisitor v) {
        v.visitDivide(this);
    }
}