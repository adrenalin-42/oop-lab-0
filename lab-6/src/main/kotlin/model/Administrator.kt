package model

open class Administrator(private val user: String,
                         private var isAdmin: Boolean = false,
                         happiness : Int = 30,
                         violence : Int = 30,
                         iq : Int = 30,
): User(user, happiness, violence, iq), PrintContent
{
    init
    {
        this.modifyHappiness(5)
        this.modifyViolence(-2)
        this.modifyIQ(3)
    }

    // toggle admin status
    fun toggleAdmin()
    {
        isAdmin = !isAdmin
    }

    // check if current user is admin
    fun checkAdmin()
    {
        if (isAdmin)
        {
            println("$user has SUPERPOWERS!")
        }
        else
        {
            println("$user is a peasant!")
        }
    }

    // print all content
    override fun printContent()
    {
        println()
        println("----------[ USER ${this.getNickName()} ]----------")
        println("Gifts: ${this.getGifts()}")
        println("Roles: ${this.getRoles()}")
        println("Happiness: ${this.getHappiness()}")
        println("Violence: ${this.getViolence()}")
        println("IQ: ${this.getIq()}")
        println("Is administrator")
        println("----------[ THE END ]----------")
        println()
    }
}