package expression.generic.calculators;

import expression.exceptions.CalculateException;

public abstract class AbstractAriph<T extends Number> {
    public abstract T add(T a, T b);

    public abstract T subtract(T a, T b);

    public abstract T multiply(T a, T b);

    protected abstract T divideImpl(T a, T b);

    public abstract T negate(T a);

    public abstract T parseConst(int value);

    public abstract T parseConst(String value);

    protected abstract int compare(T a, T b);

    public T divide(T a, T b) {
        if (this instanceof IntegerTypeAriph) {
            if (compare(b, parseConst(0)) == 0) {
                throw new CalculateException("0");
            }
        }
        return divideImpl(a, b);
    }

    public T abs(T a) {
        return compare(a, parseConst(0)) < 0 ? negate(a) : a;
    }

    public T square(T a) {
        return multiply(a, a);
    }

    public T mod(T a, T b) {
        return subtract(a, multiply(divide(a, b), b));
    }

    public abstract T count(T a);

    public abstract T min(T a, T b);

    public abstract T max(T a, T b) ;
}