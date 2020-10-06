open Funs

(***********************)
(* Part 2: Integer BST *)
(***********************)

type int_tree =
  | IntLeaf
  | IntNode of int * int_tree * int_tree

let empty_int_tree = IntLeaf

let rec int_insert x t =
  match t with
  | IntLeaf -> IntNode(x, IntLeaf, IntLeaf)
  | IntNode (y, l, r) when x > y -> IntNode (y, l, int_insert x r)
  | IntNode (y, l, r) when x = y -> t
  | IntNode (y, l, r) -> IntNode (y, int_insert x l, r)

let rec int_mem x t =
  match t with
  | IntLeaf -> false
  | IntNode (y, l, r) when x > y -> int_mem x r
  | IntNode (y, l, r) when x = y -> true
  | IntNode (y, l, r) -> int_mem x l

(* Implement the functions below. *)

(* done *)
let rec int_size t = match t with
IntLeaf -> 0
| IntNode (n, l, r) -> 1 + (int_size l) + (int_size r)

(* done *)
let rec int_max t = match t with
  IntLeaf -> raise (Invalid_argument("int_max"))
  | IntNode (n, l, r) when r != IntLeaf -> int_max r
  | IntNode (n, l, r) -> n ;;

(* done *)
let rec int_common t x y = match t with
  IntLeaf -> raise (Invalid_argument("int_common"))
  | IntNode (n, l, r) when (int_mem y l) = (int_mem y r)  && false = false && n != y  -> raise (Invalid_argument("int_common")) 
  | IntNode (n, l, r) when (int_mem x l) = (int_mem x r) && false = false && n != x -> raise (Invalid_argument("int_common")) 
  | IntNode (n, l, r) when y < n && x < n  -> int_common l x y 
  | IntNode (n, l, r) when x > n && y > n -> int_common r x y 
  | IntNode (n, l, r) -> n;;

(***************************)
(* Part 3: Polymorphic BST *)
(***************************)

type 'a atree =
    Leaf
  | Node of 'a * 'a atree * 'a atree
type 'a compfn = 'a -> 'a -> int
type 'a ptree = 'a compfn * 'a atree

let empty_ptree f : 'a ptree = (f,Leaf)

(* Implement the functions below. *)

(* helper *)
let rec insert_help x t a = match t with 
   Leaf -> Node(x,Leaf,Leaf)
  |Node(y,l,r) when a x y > 0 -> Node(y, l, insert_help x r a)
  |Node(y,l,r) when a x y = 0 -> t
  |Node(y,l,r) -> Node (y, insert_help x l a, r);;
  
(* done *)
let pinsert x t = match t with
    (a, Leaf) -> (a, Node(x, Leaf, Leaf))
    | (a, Node (y, l, r)) when a x y > 0 -> (a, Node (y, l, insert_help x r a))
    | (a, Node (y, l, r)) when a x y = 0 -> t
    | (a, Node (y, l, r)) -> (a, Node (y, insert_help x l a, r));;
  
(* done *)
let rec is_member x t a = match t with 
  Leaf -> false
  | Node (y, l, r) when a x y > 0 -> false || is_member x r a
  | Node (y, l, r) when a x y = 0 -> true
  | Node (y, l, r) -> false || is_member x l a;;

(* done *)
let pmem x t = match t with 
  (a, Leaf) -> false
| (a, Node (y, l, r)) when a x y > 0 -> false || is_member x r a
| (a, Node (y, l, r)) when a x y = 0 -> true
| (a, Node (y, l, r)) -> false || is_member x l a;;

(* done *)
let pinsert_all lst t = List.fold_left (fun acc x -> pinsert x acc) t lst;;

(* done *)
let rec list_helper t = match t with 
  Leaf -> []
  | Node (y, l, r) -> list_helper l @ [y] @ list_helper r;;

(* done *)
let rec p_as_list t = match t with 
  (a, Leaf) -> []
  | (a, Node (y, l, r)) -> list_helper l @ [y] @ list_helper r;;

(* done *)
let rec pmap f t = match t with
    (a, _) -> let lst = List.map f (p_as_list t) in
        let return_tree = empty_ptree a in
          pinsert_all lst return_tree;;

(***************************)
(* Part 4: Variable Lookup *)
(***************************)

type scope = 
  | No_Scope
  | Scope of (string * int) list;;

type lookup_table = scope list;;

let empty_table () : lookup_table = [];;

(* done *)
let push_scope (table: lookup_table) : lookup_table = [No_Scope]@table ;;

(* done *)
let pop_scope (table: lookup_table) : lookup_table = match table with
  [] -> failwith "No scopes remain!"
  | h::t -> t;;

  (* done *)
let rec add_scope l name value = match l with
  [] -> [(name, value)] @ l
  | h::t -> match h with
          |(n, v) -> if n = name then [(n, value)] @ t else (add_scope t name value) @ l;;
  
(* done *)
let add_in_scope scope name value = 
  match scope with
  No_Scope -> Scope[(name, value)]
  | Scope(l) -> match l with
              | _ -> Scope(add_scope l name value);;

(* done *)
let add_var name value (table: lookup_table) : lookup_table = match table with
[] -> failwith "There are no scopes to add a variable to!"
| h::t -> [add_in_scope h name value] @ t;;

(* done *)
let rec search lst name = match lst with
  [] -> 0
  | h::t -> match h with
            | (n, v) -> if n = name then v else search t name;;

(* done *)
let rec lookup name (table: lookup_table) = match table with
  [] -> failwith "Variable not found!"
  | h::t -> match h with
            | Scope(lst) -> match lst with
                          | _ -> if search lst name = 0 then failwith "Variable not found!" else search lst name;;
