package expression;

import expression.generic.calculators.AbstractAriph;

public interface GenericExpression {
    <T extends Number> T evaluate(AbstractAriph<T> calculator, T x, T y, T z);
}
