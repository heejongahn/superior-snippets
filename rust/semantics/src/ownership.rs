fn main() {
    let mut x = 5;

    add_one(&mut x);

    println!("{}", x);
}


fn add_one(num: &mut i32) {
    *num += 1;
}
