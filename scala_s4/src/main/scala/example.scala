object Example:
    @main def run(): Unit = 
        println(fibonacci(1))
        println(fibonacci(2))
        println(fibonacci(3))
        println(fibonacci(4))
        println(fibonacci(5))
        println(fibonacci(6))
    
    def fibonacci(n: Int): Int =
        var previousResult = 0
        var result = 1
        var i = 1
        while i < n do
            val tmp = result
            result = result + previousResult
            previousResult = tmp
            i = i + 1
        result
    end fibonacci