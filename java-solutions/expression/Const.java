package expression;

import expression.generic.calculators.AbstractAriph;

public class Const implements GenericExpression {
    private final String value;

    public Const(String value) {
        this.value = value;
    }

    @Override
    public <T extends Number> T evaluate(AbstractAriph<T> calculator, T x, T y, T z) {
        return calculator.parseConst(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
