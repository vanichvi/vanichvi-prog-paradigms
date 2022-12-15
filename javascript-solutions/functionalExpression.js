"use strict";
const VARS = {
    'x': 0,
    'y': 1,
    'z': 2
};
const OPS = {
    '+': 2,
    '-': 2,
    '*': 2,
    '/': 2,
    "negate": 1,
    "abs": 1,
    "iff": 3
};

const CONSTS = {
    "pi": Math.PI,
    "e": Math.E
};

let anyOper = function (op) {
    return function () {
        let ar = arguments;
        return function () {
            let vals = [];
            for (let i = 0; i < ar.length; i++) {
                vals[i] = ar[i].apply(null, arguments);
            }
            return op.apply(null, vals);
        }
    }
};

let add = anyOper(function (x, y) {
    return x + y;
});

let subtract = anyOper(function (x, y) {
    return x - y;
});

let multiply = anyOper(function (x, y) {
    return x * y;
});

let divide = anyOper(function (x, y) {
    return x / y;
});

let negate = anyOper(function (x) {
    return -x;
});
let abs = anyOper(function (x) {
    if (x >= 0) {
        return x;
    } else {
        return -x;
    }
});

let iff = anyOper(function (x, y, z) {
    if (x >= 0) {
        return y;
    } else {
        return z;
    }
});
let variable = function (name) {
    return function () {
        if (name === 'x') {
            return arguments[0]
        } else if (name === 'y') {
            return arguments[1]
        } else {
            return arguments[2]
        }
    }
};
let cnst = function (x) {
    return function () {
        return x;
    };
};

const pi = function () {
    return CONSTS["pi"];
};

const e = function () {
    return CONSTS["e"];
};

const FUNCS = {
    "+": add,
    "-": subtract,
    "*": multiply,
    "/": divide,
    "negate": negate,
    "abs": abs,
    "iff": iff

};

let parse = function (input) {
    let tokens = input.split(/\s+/).filter(function (t) {
        return t !== '';
    });
    let stack = [];
    for (let i = 0; i < tokens.length; i++) {
        if (tokens[i] in OPS) {
            let a = [];
            for (let j = 0; j < OPS[tokens[i]]; j++) {
                a[OPS[tokens[i]] - j - 1] = stack.pop();
            }
            let op = FUNCS[tokens[i]].apply(null, a)
            stack.push(op);
        } else if (tokens[i] in VARS) {
            stack.push(variable(tokens[i]));
        } else if (tokens[i] in CONSTS) {
            stack.push(cnst(CONSTS[tokens[i]]));
        } else {
            stack.push(cnst(parseInt(tokens[i])));
        }
    }
    let res = stack.pop();
    return res;
};
