teachers = {
    'Andy': 'English',
    'Joan': 'Maths',
    'Alice': 'Computer Science',
}

# Let's swap key-value..

# using a list comprehension
subjects = dict((subject, teacher) for teacher, subject in teachers.items())

# using a dictionary comprehension
subjects = {subject: teacher for teacher, subject in teachers.items()}
