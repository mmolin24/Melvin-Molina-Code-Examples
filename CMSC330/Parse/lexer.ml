open TokenTypes

let tokenize input =
  let re_LParen = Str.regexp "(" in
  let re_RParen = Str.regexp ")" in
  let re_LBrace = Str.regexp "{" in
  let re_RBrace = Str.regexp "}" in
  let re_Greater = Str.regexp ">" in
  let re_Less = Str.regexp "<" in
  let re_GreaterEqual = Str.regexp ">=" in
  let re_LessEqual = Str.regexp "<=" in
  let re_Print = Str.regexp "printf" in
  let re_Main = Str.regexp "main" in
  let re_If = Str.regexp "if" in
  let re_Else = Str.regexp "else" in
  let re_To = Str.regexp "to" in
  let re_Equal = Str.regexp "==" in
  let re_Or = Str.regexp "||" in
  let re_And = Str.regexp "&&" in
  let re_Not = Str.regexp "!" in
  let re_Semi = Str.regexp ";" in
  let re_NotEqual = Str.regexp "!=" in
  let re_Int_Type = Str.regexp "int" in
  let re_Bool_Type = Str.regexp "bool" in
  let re_id = Str.regexp "[a-zA-Z][a-zA-Z0-9]*" in
  let re_Bool = Str.regexp "true\\|false" in
  let re_Int = Str.regexp "-?[0-9]+" in
  let re_Spaces = Str.regexp "[ \t\n]*" in
  let re_extra = Str.regexp "[a-zA-Z0-9]+" in
  let re_For = Str.regexp "for" in
  let re_From = Str.regexp "from" in
  let re_Assign = Str.regexp "=" in
  let re_While = Str.regexp "while" in
  let re_Add = Str.regexp "+" in
  let re_Sub = Str.regexp "-" in
  let re_Mult = Str.regexp "*" in
  let re_Div = Str.regexp "/" in
  let re_Pow = Str.regexp "\\^" in 

  let rec token str pos =

    if pos >= (String.length str)
      then [EOF]

    else if (Str.string_match re_LParen str pos)
      then (Tok_LParen)::(token str (pos + 1))


    else if (Str.string_match re_RParen str pos)
      then (Tok_RParen)::(token str (pos + 1))


    else if (Str.string_match re_LBrace str pos)
      then (Tok_LBrace)::(token str (pos + 1))


    else if (Str.string_match re_RBrace str pos)
      then (Tok_RBrace)::(token str (pos + 1))


    else if (Str.string_match re_Assign str pos)
      then (Tok_Assign)::(token str (pos + 1))


    else if (Str.string_match re_Or str pos)
      then (Tok_Or)::(token str (pos +2))


    else if (Str.string_match re_And str pos)
      then (Tok_And)::(token str (pos + 2))    


    else if (Str.string_match re_Equal str pos)
      then (Tok_Equal)::(token str (pos + 2))


    else if (Str.string_match re_NotEqual str pos)
      then (Tok_NotEqual)::(token str (pos + 2))
      

    else if (Str.string_match re_Greater str pos)
      then (Tok_Greater)::(token str (pos + 1))


    else if (Str.string_match re_Less str pos)
      then (Tok_Less)::(token str (pos + 1))


    else if (Str.string_match re_GreaterEqual str pos)
      then (Tok_GreaterEqual)::(token str (pos + 2))


    else if (Str.string_match re_LessEqual str pos)
      then (Tok_LessEqual)::(token str (pos + 2))


    else if (Str.string_match re_Not str pos)
      then (Tok_Not)::(token str (pos + 1))


    else if (Str.string_match re_Semi str pos)
      then (Tok_Semi)::(token str (pos + 1))


    else if (Str.string_match re_Print str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_Print)::(token str new_pos)


    else if (Str.string_match re_From str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_From)::(token str new_pos)


    else if (Str.string_match re_Int_Type str pos) (* DOUBLE CHECK *)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_Int_Type)::(token str new_pos)


    else if (Str.string_match re_If str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_If)::(token str new_pos)


    else if (Str.string_match re_Else str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_Else)::(token str new_pos)


    else if (Str.string_match re_Bool_Type str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_Bool_Type)::(token str new_pos)


    else if (Str.string_match re_Main str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_Main)::(token str new_pos)


    else if (Str.string_match re_For str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_For)::(token str new_pos)


    else if (Str.string_match re_To str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_To)::(token str new_pos)


    else if (Str.string_match re_While str pos)
      then let token_str = Str.matched_string str in
        let new_pos = Str.match_end() in
        if (Str.string_match re_extra str new_pos)
          then let token_word = Str.matched_string str in
          (Tok_ID (token_str^token_word))::(token str (Str.match_end()))
        else (Tok_While)::(token str new_pos)


    else if (Str.string_match re_Add str pos)
      then (Tok_Add)::(token str (pos + 1))


    else if (Str.string_match re_Sub str pos)
      then (Tok_Sub)::(token str (pos + 1))


    else if (Str.string_match re_Mult str pos)
      then (Tok_Mult)::(token str (pos + 1))


    else if (Str.string_match re_Div str pos)
      then (Tok_Div)::(token str (pos + 1))


    else if (Str.string_match re_Pow str pos)
      then (Tok_Pow)::(token str (pos + 1))


    else if (Str.string_match re_Bool str pos)
      then let t = Str.matched_string str in
        (Tok_Bool (bool_of_string t))::(token str (Str.match_end()))


    else if (Str.string_match re_Int str pos)
      then let t = Str.matched_string str in
        (Tok_Int (int_of_string t))::(token str (Str.match_end()))

        
    else if (Str.string_match re_id str pos)
      then let t = Str.matched_string str in
        (Tok_ID t)::(token str (Str.match_end()))

        
    else if (Str.string_match re_Spaces str pos)
      then token str (Str.match_end())

    else raise (InvalidInputException "Error with lexer")

    in token input 0