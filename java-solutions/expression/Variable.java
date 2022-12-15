package expression;

import expression.generic.calculators.AbstractAriph;

public class Variable implements GenericExpression {
    private final String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public <T extends Number> T evaluate(AbstractAriph<T> calculator, T x, T y, T z) {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            case "z":
                return z;
        }
        throw new IllegalArgumentException("Unknown");
    }

    @Override
    public String toString() {
        return name;
    }
}
