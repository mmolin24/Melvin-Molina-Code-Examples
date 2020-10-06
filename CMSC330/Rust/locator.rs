use std::cmp::Ordering;
use std::collections::HashMap;
use std::mem::swap;

pub trait PriorityQueue<T: PartialOrd> {
    fn enqueue(&mut self, ele: T) -> ();
    fn dequeue(&mut self) -> Option<T>;
    fn peek(&self) -> Option<&T>;
    fn constHeap(&mut self, ele: usize) -> ();
}

/**
    An optional definition of a Node struct you may find useful
**/
struct Node<T> {
    priority: i32,
    data: T,
}

/**
    These traits are implemented for Nodes to make them comparable
**/
impl<T> PartialOrd for Node<T> {
    fn partial_cmp(&self, other: &Node<T>) -> Option<Ordering> {
        self.priority.partial_cmp(&other.priority)
    }
}

impl<T> PartialEq for Node<T> {
    fn eq(&self, other: &Node<T>) -> bool {
        self.priority == other.priority
    }
}

/**
    You must implement the above trait for the vector type
**/
impl<T: PartialOrd> PriorityQueue<T> for Vec<T> {
    /**
        This functions pushes a given element onto the queue and
        reorders the queue such that the min heap property holds.
        See the project specifications for more details on how this
        works.
    **/
    fn enqueue(&mut self, ele: T) -> () {
       self.push(ele);
        // places element into array
       let mut ind: usize = self.len() -1;

       while ind != 0 && self[(ind-1)/2] > self[ind] {
            self.swap(ind, (ind-1)/2);
            ind = (ind -1)/2;
       }
    }

    /**
        This function removes the root element from the queue and
        reorders the queue such that it maintains the min heap
        property.  See the project specifications for more details.
        You should return the deleted element in the form of an option.
        Return None if the queue was initially empty, Some(T) otherwise.
    **/
    fn dequeue(&mut self) -> Option<T> {

       if self.len() == 0 {
        // size is 0 so return None option<>
        return None

       }else{
        // finds the last value of the array
        let mut count: usize = self.len() -1;
        // swap the last position and the first 
        self.swap(0, count);

        // save value of the previous first element to then return as Option
        let ret = self.remove(self.len() -1);
        
        self.constHeap(0);
        // returns the Some with the last value that was in the array
        return Some(ret);
        }
    }

    // function used to Construct the heap taking in the self and the ele 
     fn constHeap(&mut self, ele: usize) -> () {

        let mut begin: usize = ele;

        // checks the left child
        if ((2 * ele) + 1) < self.len() && self[(2 * ele) + 1] < self[ele] {
            // uses ele*2 +1 as the formula to get array leftchild 
            begin = ele*2 + 1;

        }
        if ((2 * ele) + 2) < self.len() && self[(2 * ele) + 2] < self[begin] {
            // checks the right child using formula to get the rightchild
            begin = ele*2 + 2;

        }
        if begin != ele {
        // continues 
        self.swap(ele, begin);
        self.constHeap(begin);

        }
    }


    /**
        This function returns the element that would be removed
        if dequeue were called on the queue.  There should be no
        mutations to the queue.  Return the element in the form
        of an option.  Return None if the queue is empty, Some(T)
        otherwise.
    **/
    fn peek(&self) -> Option<&T> {

        if self.len() == 0{
            // returns none because array is empty
            return None

        }else{
            // returns some of the parent node/root 
            return Some(&self[0]);

        }
    }
}


/**
    You must implement this function that computes the orthogonal
    distance between two coordinates.  Remember, orthogonal distance
    is not like Euclidean distance.  See the specifications for more
    details.
**/
pub fn distance(p1: (i32,i32), p2: (i32,i32)) -> i32 {
    match p1{
        (x1,y1) => match p2 { // uses pattern matching to then calculate the distance 
             
            (x2,y2) => return ((x1-x2).pow(2) as f64).sqrt() as i32 + ((y1-y2).pow(2) as f64).sqrt() as i32
        }
    }
}

/**
    You must implement this function that determines which enemy Stark
    should battle and their coordinates.  You are given two hashmaps for
    allies and enemies.  Each maps a name to their current coordinates.
    You can assume that the allies hashmap will always have a name
    called "Stark" included.  Return the name and coordinates of the enemy
    Stark will battle in the form of a 3-tuple.  See the specifications
    for more details on how to choose which enemy.
**/
pub fn target_locator<'a>(allies: &'a HashMap<&String, (i32,i32)>, enemies: &'a HashMap<&String, (i32,i32)>) -> (&'a str,i32,i32) {

    let mut allyHeap = Vec::new();

    let mut enemiesver2 = Vec::new();
    let mut alliesver2 = Vec::new();


    // iterates the allies to then place into the priority queue based on distance
    for j in allies.iter(){

        match j {

            (x,(y,z)) => {
            for i in enemies.iter(){
                 match i {
                     (a,(b,c)) => {
                         // creates node using the distance from the villains
                         allyHeap.enqueue(Node {priority: distance((*y,*z),(*b,*c)) , data: x.as_str()});
                     }
                 }
            }
         }
        }
    }

    // goes through the priority queue to match up enemies and allies
    while allyHeap.len() > 0 {
        for j in allies.iter(){
                match j {
                    (x,(y,z)) => {
                    for i in enemies.iter(){
                         match i {
                             (a,(b,c)) => if distance((*y,*z),(*b,*c)) == allyHeap[0].priority{

                                  if x.as_str() == "Stark" && enemiesver2.contains(a) == false{

                                    return (a.as_str(),*b,*c);
                                  }else{

                                    if enemiesver2.contains(a) == false && alliesver2.contains(x) == false{

                                        // if neither enemy nor ally has a match then we match them both
                                        enemiesver2.push(a);

                                        alliesver2.push(x);

                                    }
                                  }
                                }
                             }
                         }
                    },
                }
        }
                // once a match is found dequeue
                allyHeap.dequeue();

    }
    // all else fails
    return("",0,0);

}