package expression.generic.calculators;

import expression.exceptions.CalculateException;

public class CheckedIntegerAriph extends IntegerAriph {
    @Override
    public Integer add(Integer a, Integer b) {
        if (b > 0 && a > Integer.MAX_VALUE - b || b < 0 && a < Integer.MIN_VALUE - b) {
            throw new CalculateException("summation overflow");
        }
        return super.add(a, b);
    }

    @Override
    public Integer subtract(Integer a, Integer b) {
        if (b < 0 && a > Integer.MAX_VALUE + b || b > 0 && a < Integer.MIN_VALUE + b) {
            throw new CalculateException("subtraction overflow");
        }
        return super.subtract(a, b);
    }

    @Override
    public Integer multiply(Integer a, Integer b) {
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b > 0 && (a > Integer.MAX_VALUE / b || a < Integer.MIN_VALUE / b) ||
            b < 0 && (a < Integer.MIN_VALUE / -b || a < Integer.MAX_VALUE / b)) {
            throw new CalculateException("multiplication overflow");
        }
        return super.multiply(a, b);
    }

    @Override
    protected Integer divideImpl(Integer a, Integer b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new CalculateException("division overflow");
        }
        return super.divideImpl(a, b);
    }

    @Override
    public Integer negate(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new CalculateException("negation overflow");
        }
        return super.negate(a);
    }

    @Override
    public Integer abs(Integer a) {
        if (a == Integer.MIN_VALUE) {
            throw new CalculateException("abs overflow");
        }
        return super.abs(a);
    }

    @Override
    public Integer square(Integer a) {
        if (a > 0 && a > Integer.MAX_VALUE / a ||
            a < 0 && (a < Integer.MIN_VALUE / -a || a < Integer.MAX_VALUE / a)) {
            throw new CalculateException("square overflow");
        }
        return super.square(a);
    }

    @Override
    public Integer mod(Integer a, Integer b) {
        if (a == Integer.MIN_VALUE && b == -1) {
            throw new CalculateException("division overflow");
        }
        return super.mod(a, b);
    }
}
