open SmallCTypes
open EvalUtils
open TokenTypes

exception TypeError of string
exception DeclareError of string
exception DivByZeroError

let rec exist env v =
  match env with
  | [] -> false
  | (x, y)::t -> if v = x then true else exist t v

let rec search env v =
  match env with
  | [] -> failwith "No environment variables"
  | (x, y)::t -> if v = x then y else (search t v)

let addon env x y = (x, y)::env;;

let rec eval_expr env t =
  match t with
  
  | Bool x -> Bool_Val x


  | Int x -> Int_Val x


  | ID x -> 
    if (exist env x) = false
    then raise (DeclareError("ID not found"))
    else (search env x) 
    

  | Pow (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Int_Val (int_of_float (float_of_int a ** float_of_int b)))
      | Bool_Val b -> raise (TypeError("Bool in pow")))
    | Bool_Val a -> raise (TypeError("Bool in pow")))
    

  | Mult (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Int_Val (a * b))
      | Bool_Val b -> raise (TypeError("Bool in mult")))
    | Bool_Val a -> raise (TypeError("Bool in mult")))
    

  | Add (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Int_Val (a + b))
      | Bool_Val b -> raise (TypeError("Bool in add")))
    | Bool_Val a -> raise (TypeError("Bool in add")))


  | Div (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> 
        if b = 0
        then raise (DivByZeroError)
        else (Int_Val (a / b))
      | Bool_Val b -> raise (TypeError("Bool in div")))
    | Bool_Val a -> raise (TypeError("Bool in div")))    


  | Sub (x1, x2) ->
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Int_Val (a - b))
      | Bool_Val b -> raise (TypeError("Bool in sub")))
    | Bool_Val a -> raise (TypeError("Bool in sub")))


  | And (x1, x2) -> 
    let bool1 = (eval_expr env x1) in
    let bool2 = (eval_expr env x2) in
    (match bool1 with
    | Int_Val a -> raise (TypeError("Int in and"))
    | Bool_Val a -> 
      (match bool2 with
      | Int_Val b -> raise (TypeError("Int in and"))
      | Bool_Val b -> (Bool_Val (a && b))))    


  | Or (x1, x2) -> 
    let bool1 = (eval_expr env x1) in
    let bool2 = (eval_expr env x2) in
    (match bool1 with
    | Int_Val a -> raise (TypeError("Int in or"))
    | Bool_Val a -> 
      (match bool2 with
      | Int_Val b -> raise (TypeError("Int in or"))
      | Bool_Val b -> (Bool_Val (a || b))))


  | Not x -> 
    let n = (eval_expr env x) in
    (match n with
    | Bool_Val y -> (Bool_Val (not y))
    | Int_Val y -> raise (TypeError("Int in not")))


  | Greater (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Bool_Val (a > b))
      | Bool_Val b -> raise (TypeError("Bool in greater")))
    | Bool_Val a -> raise (TypeError("Bool in greater")))


  | Less (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Bool_Val (a < b))
      | Bool_Val b -> raise (TypeError("Bool in less")))
    | Bool_Val a -> raise (TypeError("Bool in less")))


  | GreaterEqual (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Bool_Val (a >= b))
      | Bool_Val b -> raise (TypeError("Bool in greatereq")))
    | Bool_Val a -> raise (TypeError("Bool in greatereq")))


  | LessEqual (x1, x2) -> 
    let numb1 = (eval_expr env x1) in
    let numb2 = (eval_expr env x2) in
    (match numb1 with
    | Int_Val a ->
      (match numb2 with
      | Int_Val b -> (Bool_Val (a <= b))
      | Bool_Val b -> raise (TypeError("Bool in lesseq")))
    | Bool_Val a -> raise (TypeError("Bool in lesseq")))


  | Equal (x1, x2) -> 
    let e1 = (eval_expr env x1) in
    let e2 = (eval_expr env x2) in
    (match e1 with
    | Int_Val a ->
      (match e2 with
      | Int_Val b -> (Bool_Val (a = b))
      | Bool_Val b -> raise (TypeError("Bool in eq")))
    | Bool_Val a -> 
      (match e2 with
      | Int_Val b -> raise (TypeError("Int in eq"))
      | Bool_Val b -> (Bool_Val (a = b))))


  | NotEqual (x1, x2) -> 
    let e1 = (eval_expr env x1) in
    let e2 = (eval_expr env x2) in
    (match e1 with
    | Int_Val a ->
      (match e2 with
      | Int_Val b -> (Bool_Val (a != b))
      | Bool_Val b -> raise (TypeError("Bool in noteq")))
    | Bool_Val a -> 
      (match e2 with
      | Int_Val b -> raise (TypeError("Int in noteq"))
      | Bool_Val b -> (Bool_Val (a != b))))


let rec eval_stmt env s =
  match s with 
  | NoOp -> env

   | Seq (s1, s2) ->
    (let e1 = (eval_stmt env s1) in
    let e2 = (eval_stmt e1 s2) in e2)

  | Declare (t, s) ->
    if (exist env s) = true
    then raise (DeclareError("stmt declare error"))
    else 
      (match t with
      | Int_Type -> (addon env s (Int_Val(0)))
      | Bool_Type -> (addon env s (Bool_Val(false))))

  | Assign (s, exp) ->
    if (exist env s) = false
    then raise (DeclareError("stmt assign error 1"))
    else 
      (let s_type = search env s in
       let exp_type = (eval_expr env exp) in
       (match s_type with
        | Int_Val a -> 
          (match exp_type with
           | Int_Val b -> addon env s exp_type
           | Bool_Val b -> raise (TypeError("stmt assign error 2")))
        | Bool_Val a -> 
          (match exp_type with
           | Int_Val b -> raise (TypeError("stmt assign error 3"))
           | Bool_Val b -> addon env s exp_type))) 

  | If (exp, s1, s2) ->
    let ev = (eval_expr env exp) in
    (match ev with
    | Bool_Val x -> 
      let r = 
        (if x = true
        then (eval_stmt env s1)
        else (eval_stmt env s2)) in r
    | Int_Val x -> raise (TypeError("int in if error stmt")))

  | For (s, exp1, exp2, s1) ->
    let ev = (eval_expr env exp1) in
    (match ev with
    | Bool_Val x -> 
      (match x with
      | true -> (eval_stmt (eval_stmt env s1) (For(s, exp1, exp2, s1)))
      | false -> env)
    | Int_Val x -> raise(TypeError("Int in while stmt")))

  | While (exp, s1) ->
    let ev = (eval_expr env exp) in
    (match ev with
    | Bool_Val x -> 
      (match x with
      | true -> (eval_stmt (eval_stmt env s1) (While(exp, s1)))
      | false -> env)
    | Int_Val x -> raise(TypeError("Int in while stmt")))
           

  | Print (exp) -> 
    let ev = (eval_expr env exp) in
      (match ev with
      | Int_Val x -> 
        (print_output_int x);
        (print_output_newline());
        env
      | Bool_Val x -> 
        (print_output_bool x);
        (print_output_newline());
        env)
;;