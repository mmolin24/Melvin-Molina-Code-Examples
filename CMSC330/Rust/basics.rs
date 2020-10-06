/**
    Returns the sum 1 + 2 + ... + n
    If n is less than 0, return -1
**/
pub fn gauss(n: i32) -> i32 {
    if n >= 0 {
        return (n * (n+1))/2;
    }else{
        return -1;
    }
}

/**
    Returns the number of elements in the list that
    are in the range [s,e]
**/
pub fn in_range(lst: &[i32], s: i32, e: i32) -> i32 {

    let mut count = 0;
    for i in 0..lst.len() {
        if lst[i] >= s && lst[i] <= e {
            count = count +1;
        }
    }
    return count;
}

/**
    Returns true if target is a subset of set, false otherwise

    Ex: [1,3,2] is a subset of [1,2,3,4,5]
**/
pub fn subset<T: PartialEq>(set: &[T], target: &[T]) -> bool {

    let mut count = 0;

    for i in 0..set.len() {
        for j in 0..target.len(){
            
            if set[i] == target[j] {
                count = count +1;
            }
        }
    }
    if count == target.len() {
        return true;
    }else{
        return false;
    }
}

/**
    Returns the mean of elements in lst. If the list is empty, return None
    It might be helpful to use the fold method of the Iterator trait
**/
pub fn mean(lst: &[f64]) -> Option<f64> {
    if lst.len() == 0{
        return None;
    }else{
        return Some(lst.iter().fold(0.0, |sum,i| sum + i ) / lst.len() as f64);
    }
}

/**
    Converts a binary number to decimal, where each bit is stored in order in the array

    Ex: to_decimal of [1,0,1,0] returns 10
**/
pub fn to_decimal(lst: &[i32]) -> i32 {

    let two: i32 = 2;
    let mut sum = 0;
    let mut exp = (lst.len() -1) as i32;
    for i in 0..lst.len(){
        if lst[i] == 1{
            sum = sum + (two.pow(exp as u32));
        }
        exp = exp -1;
    }
    return sum;
}

/**
    Decomposes an integer into its prime factors and returns them in a vector
    You can assume factorize will never be passed anything less than 2

    Ex: factorize of 36 should return [2,2,3,3] since 36 = 2 * 2 * 3 * 3
**/
pub fn factorize(n: u32) -> Vec<u32> {
    let mut arr = Vec::new();
    let mut val: u32 = n;
    let mut i = 2;

    while i <= val {
        if val % i == 0{
            arr.push(i);
            val = val / i;

        }else{
            i = i+1;
        }
    }
    return arr;
}

/**
    Takes all of the elements of the given slice and creates a new vector.
    The new vector takes all the elements of the original and rotates them,
    so the first becomes the last, the second becomes first, and so on.

    EX: rotate [1,2,3,4] returns [2,3,4,1]
**/
pub fn rotate(lst: &[i32]) -> Vec<i32> {

    let mut ret = Vec::new();
    let arr = &lst;

    if(lst.len() == 0){
        return ret;
    }

    for i in 1..arr.len(){

        ret.push(arr[i]);
    }
    ret.push(arr[0]);

    return ret;
}

/**
    Returns true if target is a subtring of s, false otherwise
    You should not use the contains function of the string library in your implementation

    Ex: "ace" is a substring of "rustacean"
**/
pub fn substr(s: &String, target: &str) -> bool {
    let mut count = 0;
    let mut ind = 0;
    if s.len() == 0{
        return false;
    }
    if target.len() == 0{
        return true;
    }

    for i in 0..s.len()-1 {
    
        
        
        while ind <= target.len() && &s.chars().nth(i+ind).unwrap() == &target.chars().nth(ind).unwrap() {
            count = count +1;
                
            if count == target.len(){
                return true;
            }
            ind = ind +1;
        }
        
        count = 0;
        ind = 0;
    }
    if count == target.len() { 
        return true; 
    }else{ 
        return false;
    }
}

/**
    Takes a string and returns the first longest substring of consecutive equal characters

    EX: longest_sequence of "ababbba" is Some("bbb")
    EX: longest_sequence of "aaabbb" is Some("aaa")
    EX: longest_sequence of "xyz" is Some("x")
    EX: longest_sequence of "" is None
**/
pub fn longest_sequence(s: &str) -> Option<&str> {
    
    let mut count = 0;
    let mut count2 = 1;
    if s.len() == 0{
        return None;
    }
    let mut ret = None;

    for i in 0..(s.len()-1){
        if s[i..(i+1)] == s[(i+1)..(i+2)]{
            count2 = count2 +1;
        }else if count2 > count {
            count = count2;
            ret = Some(&s[(i - (count2 -1))..=i]);
            count2 = 1;
        }

    }
    return ret;
}
