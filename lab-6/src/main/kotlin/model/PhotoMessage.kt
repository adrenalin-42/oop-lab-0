package model

class PhotoMessage(user : User, private val photoDescription: String): Message(user)
{

    // a photo message has been sent
    init
    {
        println("$nickName sent a photo of ${this.photoDescription}")
    }

    // get voice message text
    override fun getMessage(): String
    {
        return (photoDescription)
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $photoDescription")
    }
}