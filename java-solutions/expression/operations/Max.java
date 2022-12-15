package expression.operations;

import expression.GenericExpression;
import expression.generic.calculators.AbstractAriph;

public class Max extends BinaryOperation{

    public Max(GenericExpression left, GenericExpression right){
        super(left, right);
    }
    @Override
    protected String operator() {
        return "max";
    }

    @Override
    protected <T extends Number> T eval(AbstractAriph<T> ariph, T a, T b) {
        return ariph.max(a,b);
    }
}
