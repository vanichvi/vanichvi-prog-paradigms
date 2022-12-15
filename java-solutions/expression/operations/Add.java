package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Add extends BinaryOperation {
    public Add(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String operator() {
        return "+";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> calculator, T a, T b) {
        return calculator.add(a, b);
    }
}
