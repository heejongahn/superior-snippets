import random

def guessing_game():
    while (1):
        answer = random.randint(1, 100)
        print ("Guess what? I've prepared a game for you.")
        print ("Choose a number between 1 and 100, I'll tell you if you've picked a right one.")

        while (1):
            try:
                choice = int(input())
            except ValueError:
                print ("Maybe I wasn't precise enough... Choose a INTEGER BETWEEN 1 and 100.")
                continue
            if choice == answer:
                print ("Well, here's the winner!")
                break
            elif choice < answer:
                print ("Have a gut, dude. The answer is bigger than that.")
            else:
                print ("Whoa... calm down. The answer is smaller than that.")

        cont = input('''So what do you say? If you wanna play another round,
just type "y". If not, well, bye then!''')

        if cont!='y':
            break

guessing_game()
