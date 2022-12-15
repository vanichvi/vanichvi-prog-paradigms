package expression.parser;

import expression.exceptions.*;

public class BaseParser {
    protected CharSource source;
    protected char ch = '0';

    protected void makeSource(String expression) {
        source = new StringSource(expression);
        nextChar();
    }

    protected void nextChar() {
        ch = source.hasNext() ? source.next() : '\0';
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        int len = expected.length();
        if (!source.hasNext(len - 1)) {
            return false;
        }

        for (int i = 0; i < len; i++) {
            if (source.hasNext(i) && source.next(i) == expected.charAt(i) == false) {
                return false;
            }
        }
        return !Character.isLetter(expected.charAt(len - 1)) || !source.hasNext(len) ||
                (!Character.isDigit(source.next(len)) && !Character.isLetter(source.next(len)));
    }

    protected void expect(final char c) throws ParseException {
        if (ch != c) {
            if (c == ')') {
                throw new ParseException("missing closing bracket", getPosition());
            }
            throw new ParseException("exception", getPosition());
        }
        nextChar();
    }

    protected void expect(final String value) throws ParseException {
        for (char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean between() {
        return '0' <= ch && ch <= '9';
    }

    protected void skipWhitespace() {
        while (Character.isWhitespace(ch)) {
            nextChar();
        }
    }

    protected int getPosition() {
        return source.getPosition();
    }
}
