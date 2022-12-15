package expression.generic.calculators;

import expression.exceptions.CalculateException;

import java.math.BigInteger;

public class BigIntegerAriph extends AbstractAriph<BigInteger> implements IntegerTypeAriph {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger subtract(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger multiply(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    protected BigInteger divideImpl(BigInteger a, BigInteger b) {
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger mod(BigInteger a, BigInteger b) {
        if (0 >= b.compareTo(BigInteger.ZERO)) {
            throw new CalculateException("positive");
        }
        return a.mod(b);
    }

    @Override
    public BigInteger count(BigInteger a) {
        return BigInteger.valueOf(a.bitCount());
    }

    @Override
    public BigInteger min(BigInteger a, BigInteger b) {
        return a.min(b);
    }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) {
        return a.max(b);
    }

    @Override
    public BigInteger parseConst(int value) {
        return BigInteger.valueOf(value);
    }

    @Override
    public BigInteger parseConst(String value) {
        BigInteger result;
        try {
            result = new BigInteger(value);
        } catch (NumberFormatException e) {
            throw new CalculateException("BigInteger");
        }
        return result;
    }

    @Override
    protected int compare(BigInteger a, BigInteger b) {
        return a.compareTo(b);
    }
}
