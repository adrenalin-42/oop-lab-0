package model

open class User(private var nickName: String,
                private var happiness : Int = 50,
                private var violence : Int = 50,
                private var iq : Int = 50,
) : Universe()
{
    private var gifts: MutableList<Gift> = mutableListOf()
    private var roles: MutableList<Role> = mutableListOf()

    init
    {
        println("User ${this.nickName} created!")
    }

    // send text message to server
    fun sendTextMessage(server : Server, textMessage : String, impact : List<Int>)
    {
        var tmpMessage = textMessage

        applyImpact(server, impact)

        if (happiness < 50 && violence > 40)
        {
            tmpMessage = tmpMessage.uppercase()
            this.modifyHappiness(5)
            this.modifyViolence(5)
            this.modifyIQ(-3)
            this.applyImpact(server, listOf(-1, 2, 0))
        }
        val msg = TextMessage(this, tmpMessage)
        if (server.getSaveLogs())
        {
            server.addTextLog(msg)
        }
    }

    // send voice message to server
    fun sendVoiceMessage(server : Server, voiceMessage : String, impact : List<Int>)
    {
        var tmpMessage = voiceMessage

        applyImpact(server, impact)

        if (happiness < 50 && violence > 30)
        {
            tmpMessage = tmpMessage.uppercase()
            this.modifyViolence(5)
            this.modifyHappiness(7)
            this.modifyIQ(-5)
            this.applyImpact(server, listOf(-1, 2, 0))
        }
        val msg = VoiceMessage(this, tmpMessage)
        if (server.getSaveLogs())
        {
            server.addVoiceLog(msg)
        }
    }

    // send photo message to server
    fun sendPhotoMessage(server : Server, photoMessage : String, impact : List<Int>)
    {
        var tmpMessage = photoMessage

        applyImpact(server, impact)

        if (happiness < 50 && violence > 30)
        {
            tmpMessage = tmpMessage.uppercase()
            this.modifyViolence(6)
            this.modifyHappiness(6)
            this.modifyIQ(-3)
            this.applyImpact(server, listOf(-1, 2, 0))
        }
        val msg = PhotoMessage(this, tmpMessage)
        if (server.getSaveLogs())
        {
            server.addPhotoLog(msg)
        }
    }

    private fun applyImpact(server : Server, impact : List<Int>)
    {
        val users : MutableList<User> = server.getUsers()

        for (user in users)
        {
            if (user == this)
            {
                continue
            }
            user.modifyHappiness(impact[0])
            user.modifyViolence(impact[1])
            user.modifyIQ(impact[2])
        }
    }

    // add role to user
    fun addRole(tmpRole : String, happinessLevel: Int)
    {
        val newRole = Role(tmpRole)
        modifyHappiness(happinessLevel)

        roles.add(newRole)
    }

    // remove role from user
    fun removeRole(tmpRole : Role)
    {
        roles.remove(tmpRole)
    }

    // get user nickname
    fun getNickName(): String
    {
        return (this.nickName)
    }

    // set user nickname
    fun setNickName(newName : String)
    {
        this.nickName = newName
    }

    // get user gifts
    fun setGifts(newGifts : MutableList<Gift>)
    {
        this.gifts = newGifts
    }

    // get user gifts
    fun getGifts(): MutableList<Gift>
    {
        return (this.gifts)
    }

    // set user roles
    fun setRoles(newRoles : MutableList<Role>)
    {
        this.roles = newRoles
    }

    // get user roles
    fun getRoles(): MutableList<Role>
    {
        return (this.roles)
    }

    // set user happiness
    fun setHappiness(newHappiness : Int)
    {
        this.happiness = newHappiness
    }

    // get user happiness
    fun getHappiness(): Int
    {
        return (this.happiness)
    }

    // set user happiness
    fun setViolence(newViolence : Int)
    {
        this.violence = newViolence
    }

    // get user violence
    fun getViolence(): Int
    {
        return (this.violence)
    }

    // set user happiness
    fun setIq(newIq : Int)
    {
        this.iq = newIq
    }

    // get user iq
    fun getIq(): Int
    {
        return (this.iq)
    }

    // assign user to server
    fun assignToServer(server : Server)
    {
        server.addConnection(this)
    }

    // user stats
    fun modifyHappiness(happinessLevel: Int) {
        this.happiness += happinessLevel
        if (this.happiness < 0)
            this.happiness = 0
        if (this.happiness > 100)
            this.happiness = 100
    }

    fun modifyViolence(violenceLevel: Int) {
        this.violence += violenceLevel
        if (this.violence < 0)
            this.violence = 0
        if (this.violence > 100)
            this.violence = 100
    }

    fun modifyIQ(iqLevel: Int) {
        this.iq += iqLevel
        if (this.iq < 0)
            this.iq = 0
        if (this.iq > 100)
            this.iq = 100
    }
}