package model

abstract class Message(user : User): Universe(), PrintContent
{
    internal val nickName : String = user.getNickName()
    abstract fun getMessage() : String

    // get user nickName
    fun getNickName() : String
    {
        return (nickName)
    }
}