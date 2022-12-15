package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Min extends BinaryOperation {

    public Min(GenericExpression left, GenericExpression right) {
        super(left, right);
    }

    @Override
    protected String operator() {
        return "min";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> ariph, T a, T b) {
        return ariph.min(a,b);
    }
}
