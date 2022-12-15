package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Divide extends BinaryOperation {
    public Divide(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String operator() {
        return "/";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> ariph, T a, T b) {
        return ariph.divide(a, b);
    }
}
