open List
open Sets

(*********)
(* Types *)
(*********)

type ('q, 's) transition = 'q * 's option * 'q

type ('q, 's) nfa_t = {
  sigma: 's list;
  qs: 'q list;
  q0: 'q;
  fs: 'q list;
  delta: ('q, 's) transition list;
}

(***********)
(* Utility *)
(***********)

(* explode converts a string to a character list *)
let explode (s: string) : char list =
  let rec exp i l =
    if i < 0 then l else exp (i - 1) (s.[i] :: l)
  in
  exp (String.length s - 1) []

(****************)
(* Part 1: NFAs *)
(****************)


let rm new_qs eacc acc = match new_qs with
  | h::t -> if List.mem h eacc then acc else h::acc
  | [] -> acc
;;

let rec finalize qs fs = match qs with
  | h::t when List.mem h fs -> true
  | h::t -> finalize t fs
  | [] -> false
;;


let rec removedup ls acc = match ls with
  | h::t -> if List.mem h acc then removedup t acc else removedup t (h::acc)
  | [] -> acc
;;


let move (nfa: ('q,'s) nfa_t) (qs: 'q list) (s: 's option) : 'q list =
  let rec f x acc = match x with
    | (st,tr,e)::t -> if List.mem st qs && tr = s && not(List.mem e acc) then f t (e::acc) else f t acc

    | [] -> acc
  in f nfa.delta []
;;


let e_closure (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list = let rec f nf q acc = match q with
    | h::t -> let newqs = (move nfa [h] None) in let nq = rm newqs acc [] in f nfa (nq@t) (h::acc@newqs)

    | [] -> acc
  in let ls2 = f nfa qs qs in removedup ls2 []
;;



let accept (nfa: ('q,char) nfa_t) (s: string) : bool =
  let rec check str states = match str with
    | h::t -> let states1 = e_closure nfa states in let moves = move nfa states1 (Some h)
      in if moves = [] then false else check t moves
    | [] -> if finalize (e_closure nfa states) nfa.fs then true else false
  in check (explode s) [nfa.q0]
;;

(*******************************)
(* Part 2: Subset Construction *)
(*******************************)

let new_states (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list list =
  let rec f s acc = match s with
    | h::t -> f t (acc@[(e_closure nfa (move nfa qs (Some h)))])
    | [] -> acc
  in f nfa.sigma []
;;


let new_trans (nfa: ('q,'s) nfa_t) (qs: 'q list) : ('q list, 's) transition list =
  let rec f s acc = match s with
    | h::t -> f t (acc@[(qs,Some h,e_closure nfa (move nfa qs (Some h)))])

    | [] -> acc
in f nfa.sigma []
;;


let new_finals (nfa: ('q,'s) nfa_t) (qs: 'q list) : 'q list list =
  if (finalize qs nfa.fs) then [qs] else [];;


let update e (dfa: ('q list, 's) nfa_t) =
  {dfa with qs = e::dfa.qs};;


let rec nfa_to_dfa_step (nfa: ('q,'s) nfa_t) (dfa: ('q list, 's) nfa_t)
    (work: 'q list list) : ('q list, 's) nfa_t = match work with
  | [] -> dfa
  | hw::tw -> (let rec f s dfa_ac tw2 = match s with
      | [] -> nfa_to_dfa_step nfa dfa_ac tw2
      | hs::ts -> (let a = move nfa (e_closure nfa hw) (Some hs) in
                   let e = List.sort (compare) (e_closure nfa a) in (if List.length e != 0 then

                   let newdfa = (if not(List.mem e dfa_ac.qs) then update e dfa_ac else dfa_ac) in

                   let tw1 = (if not(List.mem e dfa_ac.qs) then  e::tw2  else tw2) in

                   let newdfa2 = {newdfa with delta=(hw,Some hs,e)::newdfa.delta} in

                   let newdfa3 = (if finalize e nfa.fs then {newdfa2 with fs = newdfa2.fs@[e]} else newdfa2) in

                   f ts newdfa3 tw1 else f ts dfa_ac tw2))
     in f nfa.sigma dfa tw);
;;


let cut dfa nfa = if finalize (List.hd dfa.fs) nfa.fs then {dfa with fs = removedup(dfa.fs) []} else {dfa with fs = removedup(List.tl dfa.fs) []}
;;


let nfa_to_dfa (nfa: ('q,'s) nfa_t) : ('q list, 's) nfa_t =
  let dub = List.sort (compare) (e_closure nfa [nfa.q0]) in
  let dfa = {
    sigma = nfa.sigma;
    qs = [dub];
    q0 = dub;
    fs = [dub];
    delta = []
  }
  in cut(nfa_to_dfa_step nfa dfa dfa.qs) nfa
;;