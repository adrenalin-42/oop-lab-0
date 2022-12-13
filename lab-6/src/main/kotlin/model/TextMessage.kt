package model

class TextMessage(user : User, private val textMessage: String): Message(user)
{

    // a message has been sent
    init
    {
        println("$nickName says ${this.textMessage}")
    }

    // get message text
    override fun getMessage(): String
    {
        return (textMessage)
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $textMessage")
    }
}