package expression.generic.calculators;

import expression.exceptions.CalculateException;

public class DoubleAriph extends AbstractAriph<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double subtract(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double multiply(Double a, Double b) {
        return a * b;
    }

    @Override
    protected Double divideImpl(Double a, Double b) {
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double mod(Double a, Double b) {
        return a % b;
    }

    @Override
    public Double count(Double a) {
        return (double) Long.bitCount(Double.doubleToLongBits(a));
    }

    @Override
    public Double min(Double a, Double b) {
        return Double.min(a, b);
    }

    @Override
    public Double max(Double a, Double b) {
        return Double.max(a, b);
    }

    @Override
    public Double parseConst(int value) {
        return (double) value;
    }

    @Override
    public Double parseConst(String value) {
        try {
            return Double.valueOf(value);
        } catch (NumberFormatException e) {
            throw new CalculateException("double");
        }
    }

    @Override
    public int compare(Double a, Double b) {
        return a.compareTo(b);
    }
}
