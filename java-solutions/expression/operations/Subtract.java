package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Subtract extends BinaryOperation {
    public Subtract(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String operator() {
        return "-";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> ariph, T a, T b) {
        return ariph.subtract(a, b);
    }
}
