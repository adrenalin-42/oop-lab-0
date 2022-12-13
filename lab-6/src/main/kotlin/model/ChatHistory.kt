package model

class ChatHistory(): Universe(), PrintContent
{
    private val history : MutableList<Any> = mutableListOf()

    // initiate chat history
    init
    {
        println("Chat history started..")
    }

    // log text
    fun internalAddTextLog(msg : TextMessage)
    {
        history.add(msg)
    }

    // log voice
    fun internalAddVoiceLog(msg : VoiceMessage)
    {
        history.add(msg)
    }

    // log photo
    fun internalAddPhotoLog(msg : PhotoMessage)
    {
        history.add(msg)
    }

    // get text log
    fun getLog(): MutableList<Any>
    {
        return (history)
    }

    // check all history of chat
    override fun printContent()
    {
        for (msg in history)
        {
            if (msg !is Message)
            {
                return
            }

            val nickName = msg.getNickName()

            when (msg) {
                is TextMessage -> {
                    val message = msg.getMessage()
                    println("$nickName: $message")
                }

                is VoiceMessage -> {
                    val message = msg.getMessage()
                    println("$nickName (via voice): $message")
                }

                is PhotoMessage -> {
                    val message = msg.getMessage()
                    println("$nickName sent a photo saying $message")
                }
            }
        }
    }
}