package testing

def add(x: Int, y: Int): Int =
    x + y
end add

def fibonacci(n :Int): Int =
    def loop(remaining: Int, x1: Int, x2: Int): Int =
        if remaining == 1 then
            x1
        else
            loop(remaining - 1, x2, x1 + x1)
    loop(n,0,1)
end fibonacci
