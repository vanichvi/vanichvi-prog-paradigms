package expression.generic;

import expression.GenericExpression;
import expression.exceptions.CalculateException;
import expression.exceptions.ParseException;
import expression.generic.calculators.*;
import expression.parser.ExpressionParser;

import java.util.Arrays;
import java.util.Map;

public class GenericTabulator implements Tabulator {
    private AbstractAriph<?> getAriph(String mode) {
        // :NOTE: Экземпляры
        return switch (mode) {
            case "i" -> new CheckedIntegerAriph();
            case "d" -> new DoubleAriph();
            case "bi" -> new BigIntegerAriph();
            case "u" -> new IntegerAriph();
            case "l" -> new LongAriph();
            case "f" -> new FloatAriph();
            default -> new IntegerAriph();
        };
    }

        @Override
        public Object[][][] tabulate ( final String mode, final String expression, final int x1, final int x2,
        final int y1, final int y2, final int z1, final int z2) throws ParseException {
            final AbstractAriph<?> calculator = getAriph(mode);
            final GenericExpression expr = new ExpressionParser().parse(expression);
            return makeTable(calculator, expr, x1, x2, y1, y2, z1, z2);
        }

    <T extends Number > Object[][][]makeTable(
        final AbstractAriph<T> calculator, final GenericExpression expr,
        final int x1, final int x2, final int y1, final int y2, final int z1, final int z2){
            final Object[][][] res = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];
            for (int x = 0; x <= x2 - x1; x++) {
                for (int y = 0; y <= y2 - y1; y++) {
                    for (int z = 0; z <= z2 - z1; z++) {
                        try {
                            res[x][y][z] = expr.evaluate(calculator, calculator.parseConst(x1 + x),
                                    calculator.parseConst(y1 + y), calculator.parseConst(z1 + z));
                            // :NOTE: ArithmeticException
                        } catch (final CalculateException e) {
                            // null
                        }
                    }
                }
            }
            return res;
        }

        public static void main ( final String[] args){
            if (args.length < 2) {
                System.out.println("MODE EXPRESSION");
                return;
            }
            final int first = -2;
            final int second = 2;
            final Object[][][] res;
            String mode = args[0];
            String expr = args[1];
            try {
                res = new GenericTabulator().tabulate(mode.substring(1), expr, first, second, first, second, first, second);
            } catch (final ParseException e) {
                System.out.println("Parse error: ");
                return;
            }
            System.out.println(expr);
            for (int x = 0; x <= second - first; x++) {
                for (int y = 0; y <= second - first; y++) {
                    for (int z = 0; z <= second - first; z++) System.out.println(Arrays.deepToString(res));
                }
            }
        }
    }
