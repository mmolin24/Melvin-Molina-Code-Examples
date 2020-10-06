open SmallCTypes
open Utils
open TokenTypes

(* Parsing helpers (you don't need to modify these) *)

(* Return types for parse_stmt and parse_expr *)
type stmt_result = token list * stmt
type expr_result = token list * expr

(* Return the next token in the token list, throwing an error if the list is empty *)
let lookahead (toks : token list) : token =
  match toks with
  | [] -> raise (InvalidInputException "No more tokens")
  | h::_ -> h

(* Matches the next token in the list, throwing an error if it doesn't match the given token *)
let match_token (toks : token list) (tok : token) : token list =
  match toks with
  | [] -> raise (InvalidInputException(string_of_token tok))
  | h::t when h = tok -> t
  | h::_ -> raise (InvalidInputException(
      Printf.sprintf "Expected %s from input %s, got %s"
        (string_of_token tok)
        (string_of_list string_of_token toks)
        (string_of_token h)
    ))

(* Parsing (TODO: implement your code below) *)

let rec parse_expr toks : expr_result =  (* DONE *)
  parse_Or toks
  and parse_Or list = 
    let (list2, x1) = parse_And list in
      match list2 with
      | Tok_Or::t -> let list3 = match_token list2 Tok_Or in 
          let (list4, x2) = parse_Or list3 in (list4, Or(x1,x2))

      | _ -> (list2, x1)  

  and parse_And list =  (* yes *)
    let (list2, x1) = parse_Equals list in
      match list2 with
      | Tok_And::t -> let list3 = match_token list2 Tok_And in 
        let (list4, x2) = parse_And list3 in (list4, And(x1,x2))

      | _ -> (list2, x1)

  and parse_Pow list = 
    let (list2, x1) = parse_Not list in
      match list2 with
      | Tok_Pow::t -> let list3 = match_token list2 Tok_Pow in 
        let (list4, x2) = parse_Pow list3 in (list4, Pow(x1,x2))

      | _ -> (list2, x1) 
      
  and parse_Math list = 
    let (list2, x1) = parse_Math2 list in
      match list2 with
      | Tok_Add::t -> let list3 = match_token list2 Tok_Add in
        let (list4, x2) = parse_Math list3 in (list4, Add(x1,x2))

      | Tok_Sub::t -> let list3 = match_token list2 Tok_Sub in
        let (list4, x2) = parse_Math list3 in (list4, Sub(x1,x2))

      | _ -> (list2, x1)

  and parse_Math2 list =  (* finished *)
    let (list2, x1) = parse_Pow list in
      match list2 with
      | Tok_Mult::t -> let list3 = match_token list2 Tok_Mult in 
        let (list4, x2) = parse_Math2 list3 in (list4, Mult(x1,x2))

      | Tok_Div::t -> let list3 = match_token list2 Tok_Div in 
        let (list4, x2) = parse_Math2 list3 in (list4, Div(x1,x2))

      | _ -> (list2, x1) 

  and parse_Not list = 
    match list with
    | Tok_Not::t -> let list2 = match_token list Tok_Not in
      let (list3, x2) = parse_Not list2 in (list3, Not(x2))

    | _ -> parse_Type list     

  and parse_Equals list =   
      let (list2, x1) = parse_Comparison list in  (* double *)
      match list2 with
      | Tok_Equal::t -> let list3 = match_token list2 Tok_Equal in
        let (list4, x2) = parse_Equals list3 in (list4, Equal(x1,x2))

      | Tok_NotEqual::t -> let list3 = match_token list2 Tok_NotEqual in
        let (list4, x2) = parse_Equals list3 in (list4, NotEqual(x1,x2))

      | _ -> (list2, x1)
  
  and parse_Comparison list = 
      let (list2, x1) = parse_Math list in 
      match list2 with
      | Tok_Less::t -> let list3 = match_token list2 Tok_Less in
        let (list4, x2) = parse_Comparison list3 in (list4, Less(x1,x2))

      | Tok_Greater::t -> let list3 = match_token list2 Tok_Greater in
        let (list4, x2) = parse_Comparison list3 in (list4, Greater(x1,x2))

      | Tok_LessEqual::t -> let list3 = match_token list2 Tok_LessEqual in
        let (list4, x2) = parse_Comparison list3 in (list4, LessEqual(x1,x2))

      | Tok_GreaterEqual::t -> let list3 = match_token list2 Tok_GreaterEqual in
        let (list4, x2) = parse_Comparison list3 in (list4, GreaterEqual(x1,x2))

      | _ -> (list2, x1) 

  and parse_Type list =  (* double check *)
    match list with
    | (Tok_Int(x))::t -> let list2 = match_token list (Tok_Int(x)) in (list2, Int(x))

    | (Tok_Bool(x))::t -> let list2 = match_token list (Tok_Bool(x)) in (list2, Bool x)

    | (Tok_ID(x))::t -> let list2 = match_token list (Tok_ID(x)) in (list2, ID x)

    | Tok_LParen::t -> let list2 = match_token list Tok_LParen in
      (let (list3, x2) = parse_expr list2 in
      match list3 with
        | Tok_RParen::t -> (t, x2)
        | _ -> raise (InvalidInputException("No right parentheses")))

    | _ -> let x = (List.hd list) in
      raise (InvalidInputException("parse_expr"))
  ;;




  let rec parse_stmt toks : stmt_result = (* finished *)
    match toks with
    | Tok_Int_Type::t -> 
      let (list, value) = typeStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(value, state))
      
    | Tok_While::t -> 
      let (list, whilestate) = whileStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(whilestate, state))

    | Tok_Bool_Type::t -> 
      let (list, bool) = typeStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(bool, state))

      
    | Tok_For::t ->
      let (list, forstate) = forStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(forstate, state))


    | Tok_Print::t -> 
      let (list, prnt) = printStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(prnt, state))  


    | Tok_ID(x)::t -> 
      let (list, id) = idStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(id, state))


    | Tok_If::t -> 
      let (list, ifstate) = ifStatement toks in
      let (list2, state) = parse_stmt list in
      (list2, Seq(ifstate, state))

    | _ -> (toks, NoOp)

  and typeStatement list= 
    match list with
    | (Tok_Int_Type)::t -> let list2 = match_token list Tok_Int_Type in
      (match list2 with
        | (Tok_ID(x))::t ->   
          let list3 = match_token list2 (Tok_ID(x)) in
          let list4 = match_token list3 Tok_Semi in
          (list4, Declare(Int_Type, x))
        | _ -> raise (InvalidInputException("Int Type error typeStatement")))
    
    | Tok_Bool_Type::t -> let list2 = match_token list Tok_Bool_Type in
      (match list2 with
        | (Tok_ID(x))::t ->
          let list3 = match_token list2 (Tok_ID(x)) in
          let list4 = match_token list3 Tok_Semi in
          (list4, Declare(Bool_Type, x))
        | _ -> raise (InvalidInputException("Bool Type error typeStatement inner")))
    | _ -> raise (InvalidInputException("Bool Type error typeStatement outer"))
  
  and idStatement list = 
    match list with
    | (Tok_ID(x))::t -> let list2 = match_token list (Tok_ID(x)) in
        let list3 = match_token list2 Tok_Assign in
        let (list4, exp) = (parse_expr list3) in 
        let list5 = match_token list4 Tok_Semi in
        (list5, Assign(x, exp))
    | _ -> raise (InvalidInputException("Error idStatement"))

  and printStatement list = 
    match list with  
    | Tok_Print::t -> let list2 = match_token list Tok_Print in
        let list3 = match_token list2 Tok_LParen in
        let (list4, exp) = parse_expr list3 in
        let list5 = match_token list4 Tok_RParen in 
        let list6 = match_token list5 Tok_Semi in
        (list6, Print(exp))
    | _ -> raise (InvalidInputException("Error printStatement"))

  and forStatement list = 
    match list with  
    | Tok_For::t -> let list2 = match_token list Tok_For in
        let list3 = match_token list2 Tok_LParen in
         match list3 with 
         | (Tok_ID(x))::t -> let listid = match_token list3 (Tok_ID(x)) in
        let list4 = match_token listid Tok_From in
        let (list5, exp) = parse_expr list4 in
        let list6 = match_token list5 Tok_To in
        let (list7, exp2) = parse_expr list6 in
        let list8 = match_token list7 Tok_RParen in 
        let list9 = match_token list8 Tok_LBrace in
        let (list10, stmt) = parse_stmt list9 in
        let list11 = match_token list10 Tok_RBrace in (list11, For(x, exp, exp2, stmt))	
    | _ -> raise (InvalidInputException("Error forStatement"))
  and ifStatement list = 
    match list with    
    | Tok_If::t -> let list2 = match_token list Tok_If in
      let list3 = match_token list2 Tok_LParen in
      let (list4, if_exp) = parse_expr list3 in
      let list5 = match_token list4 Tok_RParen in
  
      let list6 = match_token list5 Tok_LBrace in
      let (list7, if_stmt) = parse_stmt list6 in
      let list8 = match_token list7 Tok_RBrace in
      
        match list8 with
        | Tok_Else::t -> let list9 = match_token list8 Tok_Else in
          let list10 = match_token list9 Tok_LBrace in
          let (list11, else_stmt) = parse_stmt list10 in
          let list12 = match_token list11 Tok_RBrace in
          (list12, If(if_exp, if_stmt, else_stmt))  
        | _ -> (list8, If(if_exp, if_stmt, NoOp))
    
    | _ -> raise (InvalidInputException("Error ifStatement")) 


  and whileStatement list = 
    match list with		
    | Tok_While::t -> let list2 = match_token list Tok_While in
      let list3 = match_token list2 Tok_LParen in
      let (list4, exp) = parse_expr list3 in
      let list5 = match_token list4 Tok_RParen in
      let list6 = match_token list5 Tok_LBrace in
      let (list7, stmt) = parse_stmt list6 in
      let list8 = match_token list7 Tok_RBrace in (list8, While(exp, stmt))	
    | _ -> raise (InvalidInputException("Error whileStatement"))
  ;;

  let parse_main toks : stmt = 
    let list = match_token toks Tok_Int_Type in
    let list1 = match_token list Tok_Main in
    let list2 = match_token list1 Tok_LParen in
    let list3 = match_token list2 Tok_RParen in 
    let list4 = match_token list3 Tok_LBrace in 
    let (list4, stmt) = parse_stmt list4 in
    let list5 = match_token list4 Tok_RBrace in
    let list6 = match_token list5 EOF in stmt 
  ;;
