package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Count extends UnaryOperation {

    public Count(GenericExpression expression) {
        super(expression);
    }

    @Override
    protected String operator() {
        return "count";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> calculator, T a) {
        return calculator.count(a);
    }
}
