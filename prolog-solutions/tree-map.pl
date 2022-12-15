map_build([], nil).
map_build([(Key, Value) | L], Tree) :- map_build(L, NewTree), map_put(NewTree, Key, Value, Tree).

separate(nil, J, nil, nil) :- !.
separate(tree((Key, Value, P), L, R), J, RL, RR) :- J < Key, separate(L, J, RL, NewL), RR = tree((Key, Value, P), NewL, R), !.
separate(tree((Key, Value, P), L, R), J, RL, RR) :- J >=Key, separate(R, J, NewR, RR), RL = tree((Key, Value, P), L, NewR), !.

merge(nil, nil, nil) :- !.
merge(nil, Tree, Tree) :- !.
merge(Tree, nil, Tree) :- !.
merge(tree((LKey, LValue, LP), LL, LR), tree((RKey, RValue, RP), RL, RR), Tree) :- LP > RP, merge( LR, tree((RKey, RValue, RP), RL, RR), NewR), Tree = tree((LKey, LValue, LP), LL, NewR), !.
merge(tree((LKey, LValue, LP), LL, LR), tree((RKey, RValue, RP), RL, RR), Tree) :- LP =<RP, merge(tree((LKey, LValue, LP), LL, LR), RL, NewL), Tree = tree((RKey, RValue, RP), NewL, RR), !.

put(nil, (Key, Value, P), tree((Key, Value, P), nil, nil)).
put(tree((Key, Value, P), L, R), (NewKey, NewValue, NewP), tree((NewKey, NewValue, NewP), NewL, NewR)) :- NewP > P, separate(tree((Key, Value, P), L, R), NewKey, NewL, NewR).
put(tree((Key, Value, P), L, R), (NewKey, NewValue, NewP), tree((Key, Value, P), NewNew, R)) :- NewP =<P, NewKey < Key, put(L, (NewKey, NewValue, NewP), NewNew).
put(tree((Key, Value, P), L, R), (NewKey, NewValue, NewP), tree((Key, Value, P), L, NewNew)) :- NewP =<P, NewKey >=Key, put(R, (NewKey, NewValue, NewP), NewNew).

map_remove(nil, _, nil).
map_remove(tree((Key, _, _), L, R), Key, RTree) :- merge( L, R, RTree).
map_remove(tree((Key, Value, P), L, R), NewKey, tree((Key, Value, P), NewNew, R)) :- NewKey < Key, map_remove(L, NewKey, NewNew).
map_remove(tree((Key, Value, P), L, R), NewKey, tree((Key, Value, P), L, NewNew)) :- NewKey > Key, map_remove(R, NewKey, NewNew).

find(nil, _, _, 1).
find(tree((Key, Value, _), _, _), Key, Value, 0).
find(tree((Key, _, _), L, _), NewKey, NewValue, Q) :- NewKey < Key, find(L, NewKey, NewValue, Q).
find(tree((Key, _, _), _, R), NewKey, NewValue, Q) :- NewKey > Key, find(R, NewKey, NewValue, Q).
map_get(Tree, Key, Value) :- find(Tree, Key, Value, Q), Q == 0.

substitute(tree((Key, _, P), L, R), Key, NewValue, tree((Key, NewValue, P), L, R)).
substitute(tree((Key, Value, P), L, R), NewKey, NewValue, tree((Key, Value, P), NewL, R)) :- NewKey < Key, substitute(L, NewKey, NewValue, NewL).
substitute(tree((Key, Value, P), L, R), NewKey, NewValue, tree((Key, Value, P), L, NewR)) :- NewKey > Key, substitute(R, NewKey, NewValue, NewR).
map_put(Tree, Key, Value, R) :- rand_int(2147483647, P), find(Tree, Key, _, Q), (Q == 0 -> substitute(Tree, Key, Value, R); put(Tree, (Key, Value, P), R)).

greatest(tree((Key, Value, P), L, nil), Key) :- !.
greatest(tree((Key, Value, P), L, R), S) :- greatest(R, S).
map_floorKey(tree((Key, Value, P), L, R), J, S) :- separate(tree((Key, Value, P), L, R), J, RL, RR), greatest(RL, S).
