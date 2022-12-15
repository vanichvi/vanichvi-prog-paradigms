package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public abstract class UnaryOperation implements GenericExpression {
    private final GenericExpression expression;

    public UnaryOperation(GenericExpression expression) {
        this.expression = expression;
    }

    protected abstract String operator();
    protected abstract <T extends Number> T eval(AbstractAriph<T> calculator, T a);


    @Override
    public <T extends Number> T evaluate(AbstractAriph<T> ariph, T x, T y, T z) {
        return eval(ariph, expression.evaluate(ariph, x, y, z));
    }

    @Override
    public String toString() {
        return String.format("%s(%s)", operator(), expression.toString());
    }

}
