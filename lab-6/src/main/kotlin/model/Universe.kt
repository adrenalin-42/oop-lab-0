package model

open class Universe()
{
    private var counter : Int = 0
    init
    {
        // in case you are wondering what the hell is going on here
        // these are lyrics from the "Big Bang Theory" series
        // println("Our whole universe was in a hot dense state,")
        // println("Then nearly fourteen billion years ago expansion started. Wait...")
        // println("The Earth began to cool,")
        // println("The autotrophs began to drool,")
        // println("Neanderthals developed tools,")
        // println("We built a wall (we built the pyramids),")
        // println("Math, science, history, unraveling the mystery,")
        // println("That all started with the big bang! (Bang!)")
    }
    fun getCounter() : Int {
        counter += 1
        return (counter - 1)
    }
}