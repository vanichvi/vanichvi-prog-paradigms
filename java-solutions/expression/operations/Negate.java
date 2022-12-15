package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Negate extends UnaryOperation {
    public Negate(GenericExpression expression) {
        super(expression);
    }

    @Override
    protected String operator() {
        return "-";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> ariph, T a) {
        return ariph.negate(a);
    }
}
