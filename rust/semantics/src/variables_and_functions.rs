fn main() {
    let x: i32 = 5;
    // x = 10;
    // Above line give you an error, since variables are IMMUTABLE in Rust.

    let mut y = 5;
    y = 10; // This works fine.

    let z :i32; // Not initialized..

    println!("Howdi doo!");
    // x,y,z will give us unused variable warning. Still, it compiles.

    // println!("The value of z is {}", z);
    // This will, of course, raise an error.

    print_number(5);
    println!("{} plus one is: {}", x, add_one(x));

    let mut a = 5;
    let a = (b = 6);  // x has the value `()`, not `6`
}

fn print_number(n: i32) {
    println!("n is: {}", n);
}

/*
 You MUST indicate the argument type when declaring funciton.
 Below will raise an error.

 fn print_number(n) {
    println!("n is: {}", n);
}
 */

fn add_one(x: i32) -> i32 {
        x + 1
}
