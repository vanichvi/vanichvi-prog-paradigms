init(MAX_N) :- prec(2, MAX_N).

m(I) :- composite(I), !.
m(I) :- assert(composite(I)).

n(I, S, MAX_N) :- I =< MAX_N, m(I), N is I + S, n(N, S, MAX_N).

prec(I, MAX_N) :- \+ composite(I), S is I * I, n(S, I, MAX_N).
prec(I, MAX_N) :- I * I =< MAX_N, N is I + 1, prec(N, MAX_N).

prime(N) :- N > 1, \+ composite(N).

d(X, A, A) :- K is div(X, A), X is K * A, prime(A), !.
d(X, A, D) :- D =< X, D1 is D + 1, d(X, A, D1), !.

ds(1, [], _).
ds(N, [N], _) :- prime(N), !.

ds(N, [A | R], P) :- d(N, A, P), N1 is div(N, A), ds(N1, R, A), !.
prime_divisors(N, D) :- \+ is_list(D), ds(N, D, 2), !.

ord([]).
ord([_]).
ord([X,Y|Z]) :- X =< Y , ord([Y|Z]), !.

mul(1, []).
mul(N, [A | R]) :- mul(T, R), N is A * T, !.
prime_ds(N, D) :- is_list(D), ord(D), mul(N, D), !.

syst(0, [], _) :- !.
syst(N, [A | R], K) :- N1 is div(N, K), A is mod(N, K), syst(N1, R, K), !.

prime_palindrome(N, K) :- prime(N), syst(N, P1, K), reverse(P1, P2), P1 = P2.
