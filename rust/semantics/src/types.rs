fn main(){
    let x = true;
    let y = 'c';

    let i = 3;
    let f = 1.32;

    let arr = [1,2,3];
    let mut marr = [1,2,3];

    let twenty_zero_arr = [0; 20];
    println!("twenty zero array has {} elements", twenty_zero_arr.len());

    let names = ["Graydon", "Brian", "Niko"]; // names: [&str; 3]
    println!("The second name is: {}", names[1]);

    let pizza = [0, 1, 2, 3, 4];
    let middle = &pizza[1..4]; // A slice of a: just the elements 1, 2, and 3
    let complete = &pizza[..]; // A slice containing all of the elements in a

    let t: (i32, &str) = (1, "freak");

    let mut t1 = (1, 2); // x: (i32, i32)
    let t2 = (2, 3); // y: (i32, i32)

    t1 = t2;

    let (e1, e2, e3) = (1, 2, 3); // Destructuring let
    println!("e1 is {}", e1);

    let tuple = (1, 2, 3);

    let et1 = tuple.0;
    let et2 = tuple.1;
    let et3 = tuple.2;

    println!("et1 is {}", et1);

    fn foo(x: i32) -> i32 { x }
    let foo_fn: fn(i32) -> i32 = foo;

}

