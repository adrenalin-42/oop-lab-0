package view

import model.Gift
import model.PrintContent
import model.Role

class UserView() {
    // print all content
    fun printContent(nickName : String,
                     gifts : MutableList<Gift>,
                     roles : MutableList<Role>,
                     happiness : Int,
                     violence : Int,
                     iq : Int,)
    {
        println()
        println("----------[ USER $nickName ]----------")
        println("Gifts: $gifts")
        println("Roles: $roles")
        println("Happiness: $happiness")
        println("Violence: $violence")
        println("IQ: $iq")
        println("----------[ THE END ]----------")
        println()
    }
}