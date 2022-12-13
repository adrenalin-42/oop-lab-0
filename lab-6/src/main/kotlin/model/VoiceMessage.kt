package model

class VoiceMessage(user : User, private val voiceMessage: String): Message(user)
{

    // a voice message has been sent
    init
    {
        println("$nickName says via a voice message ${this.voiceMessage}")
    }

    // get voice message text
    override fun getMessage(): String
    {
        return (voiceMessage)
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $voiceMessage")
    }
}