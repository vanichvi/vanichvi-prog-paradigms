package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.operations.*;

import java.util.Map;

public class ExpressionParser extends BaseParser implements GenericParser {
    private static final Map<String, Integer> U = Map.of(
            "-", 3,
            "count", 3
    );
    private static final Map<String, Integer> B = Map.of(
            "max", 0,
            "min", 0,
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    private enum Args {
        LEFT, RIGHT;

        public String get(final Operations op) {
            return op == Operations.U ? "" : this == LEFT ? "first " : "second ";
        }
    }

    private enum Operations {
        U, B;

        public String get() {
            return name().toLowerCase();
        }
    }

    @Override
    public GenericExpression parse(final String expression) throws ParseException {
        makeSource(expression);
        final GenericExpression res = parse(0, Args.LEFT);
        if (ch != '\0') {
            throw ch == ')' ? new ParseException("missing opening ", getPosition()) :
                    new ParseException("exception", getPosition());
        }
        return res;
    }

    private GenericExpression parse(final Integer priority, final Args argument) throws ParseException {
        if (priority.equals(3)) {
            return parseMost(argument, Operations.B);
        }

        GenericExpression first = parse(priority + 1, argument);
        while (checkAnyOperator(B, priority)) {
            final int pos = getPosition();
            final String op = getAnyOperator(B);
            final GenericExpression second = parse(priority + 1, Args.RIGHT);
            first = make(Operations.B, first, second, op, pos);
        }
        return first;
    }

    private GenericExpression parseMost(final Args arg, final Operations operation) throws ParseException {
        final GenericExpression expr;
        final int position = getPosition();
        skipWhitespace();
        if (test('(')) {
            expr = parse(0, Args.LEFT);
            expect(')');
        } else if (between()) {
            expr = parseConst("");
        } else if (checkAnyOperator(U, null)) {
            final String oper = getAnyOperator(U);
            skipWhitespace();
            if (test('\0') || ch == ')') {
                throw new ParseException("", getPosition());
            }
            expr = oper.equals("-") && between() ? parseConst("-") :
                    make(Operations.U, parseMost(Args.LEFT, Operations.U),
                            null, oper, position);
        } else {
            final StringBuilder sb = new StringBuilder();
            while ((Character.isLetter(ch) || between()) && ! (ch == '(' || ch == ')') && !Character.isWhitespace(ch) && !test('\0')) {
                sb.append(ch);
                nextChar();
            }
            final String item = sb.toString();
            Integer key = B.get(item);
            if (key!=null) {
                throw new ParseException("exception", position);
            }
            expr = switch (item) {
                case "x", "y", "z" -> new Variable(item);
                case "" -> throw new ParseException("variable",
                        position);
                default -> throw new ParseException(item, position);
            };
        }
        skipWhitespace();
        return expr;
    }

    private Const parseConst(final String sign) throws ParseException {
        final StringBuilder current = new StringBuilder();
        current.append(sign);
        while (between()) {
            current.append(ch);
            expect(ch);
        }

        final int next = getPosition();
        skipWhitespace();
        if (between()) {
            throw new ParseException("exception", next);
        }

        return new Const(current.toString());
    }

    private boolean checkAnyOperator(final Map<String, Integer> operations, final Integer p) {
        for (final var c : operations.entrySet()) {
            if (test(c.getKey())) {
                if (p == null) {
                    return true;
                }
                return c.getValue().equals(p);
            }
        }
        return false;
    }

    private String getAnyOperator(final Map<String, Integer> operations) throws ParseException {
        String s = "";
        for (final var current : operations.entrySet()) {
            if (test(current.getKey())) {
                s = current.getKey();
                expect(s);
                break;
            }
        }
        return s;
    }

    private GenericExpression make(final Operations op, final GenericExpression first, final GenericExpression second, final String oper, final int pos) throws ParseException {
        if (op.equals(Operations.U)) {
            switch (oper) {
                case "-":
                    return new Negate(first);
                case "count":
                    return new Count(first);
            }
        } else {
            switch (oper) {
                case "+":
                    return new Add(first, second);
                case "-":
                    return new Subtract(first, second);
                case "*":
                    return new Multiply(first, second);
                case "/":
                    return new Divide(first, second);
                case "min":
                    return new Min(first, second);
                case "max":
                    return new Max(first, second);
            }
        }
        throw new ParseException("unknown", pos);
    }
}
