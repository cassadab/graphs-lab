import enum

class Color(enum.Enum):
    white = 1
    grey = 2
    black = 3

for col in (Color):
    print(col)