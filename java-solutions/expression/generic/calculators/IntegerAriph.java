package expression.generic.calculators;

import expression.exceptions.CalculateException;

public class IntegerAriph extends AbstractAriph<Integer> implements IntegerTypeAriph {
    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }

    @Override
    protected Integer divideImpl(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer negate(Integer a) {
        return -a;
    }

    @Override
    public Integer parseConst(int value) {
        return value;
    }

    @Override
    public Integer parseConst(String value) {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new CalculateException("integer");
        }
    }

    @Override
    public int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    @Override
    public Integer count(Integer a) {
        return Integer.bitCount(a);
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return Integer.min(a, b);
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return Integer.max(a, b);
    }

}
