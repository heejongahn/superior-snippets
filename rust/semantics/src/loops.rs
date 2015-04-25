fn main() {
    for x in 0..10 {
        println!("{}", x); // x: i32
    }

    let mut y = 5; // mut x: u32
    let mut done = false; // mut done: bool

    while !done {
        y += y - 3;

        println!("{}", x);

        if y % 5 == 0 {
            done = true;
        }
    }

    // Instead of while true, you can use keyword loop
}
