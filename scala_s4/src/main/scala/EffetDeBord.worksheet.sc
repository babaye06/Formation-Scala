// Let us to implement a random number generator without using side-effects
class Generator(previous: Int):
    def nextInt(): (Int, Generator) =
        val result = previous * 22_695_477 + 1
        (result, Generator(result))

    //The between operation can be implemented like this in the class Generator
    def between(x: Int, y: Int):(Int, Generator) =
        val min = math.min(x,y)
        val delta = math.abs(x-y)
        val (randomValue, nextGenerator) = nextInt()
        (math.abs(randomValue % delta) + min, nextGenerator)
end Generator

object Generator:
    def init: Generator = Generator(42)

//Utilisation du generateur 
val gen1 = Generator.init
val (x,gen2) = gen1.nextInt()
println(x)
val (y,_) = gen1.nextInt()
println(y)
val (z,gen3) = gen2.nextInt()
println(z)


//Our random number generator can be used like this
val(windowSide, gen0) = Generator.init.between(1,4)
val windowArea = windowSide * windowSide
windowArea

def windowSideAndgen1 = Generator.init.between(1,4)
def windowAreaDef = windowSideAndgen1(0) * windowSideAndgen1(0)
windowAreaDef