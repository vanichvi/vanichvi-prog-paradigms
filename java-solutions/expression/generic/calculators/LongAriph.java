package expression.generic.calculators;

public class LongAriph extends AbstractAriph<Long> implements IntegerTypeAriph {
    @Override
    public Long add(Long a, Long b) {
        return a + b;
    }

    @Override
    public Long subtract(Long a, Long b) {
        return a - b;
    }

    @Override
    public Long multiply(Long a, Long b) {
        return a * b;
    }

    @Override
    protected Long divideImpl(Long a, Long b) {
        return a / b;
    }

    @Override
    public Long negate(Long a) {
        return -a;
    }

    @Override
    public Long parseConst(int value) {
        return (long) value;
    }

    @Override
    public Long parseConst(String value) {
        return Long.valueOf(value);
    }

    @Override
    protected int compare(Long a, Long b) {
        return a.compareTo(b);
    }

    @Override
    public Long count(Long a) {
        return (long) Long.bitCount(a);
    }

    @Override
    public Long min(Long a, Long b) {
        return Long.min(a, b);
    }

    @Override
    public Long max(Long a, Long b) {
        return Long.max(a, b);
    }
}
