package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public abstract class BinaryOperation implements GenericExpression {
    private final GenericExpression first;
    private final GenericExpression second;

    public BinaryOperation(GenericExpression first, GenericExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract String operator();
    protected abstract <T extends Number> T eval(AbstractAriph<T> calculator, T a, T b);

    @Override
    public <T extends Number> T evaluate(AbstractAriph<T> ariph, T x, T y, T z) {
        return eval(ariph, first.evaluate(ariph, x, y, z), second.evaluate(ariph, x, y, z));
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", first.toString(), operator(), second.toString());
    }
}
