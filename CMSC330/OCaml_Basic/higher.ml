open Funs

(********************************)
(* Part 1: High Order Functions *)
(********************************)

(* done *)
let count_occ lst target  = List.fold_left (fun acc h -> if h = target then acc+1 else acc) 0 lst;; 

(* done *)
let uniq lst = List.fold_left (fun a v -> if (count_occ (lst) (v) >= 1) then (if count_occ(a)(v) >= 1 then a else v::a) else a ) [] lst;;

(* done *)
let assoc_list lst =  List.fold_left(fun a v ->  (v, (count_occ lst v))::a) [] (uniq lst);;
