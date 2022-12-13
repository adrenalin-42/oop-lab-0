package model

class Gift(private var belongsTo: User,
           private var gift : String,
): Achievable()
{
    // a gift has been created
    init
    {
        belongsTo.modifyHappiness(2)
        belongsTo.modifyViolence(-2)
        belongsTo.modifyIQ(1)
    }

    fun getGiftDescription(): String
    {
        return (gift)
    }

    fun setGiftDescription(giftDescription: String)
    {
        this.gift = giftDescription
    }

    fun getOwner(): User
    {
        return (belongsTo)
    }

    fun setOwner(newOwner: User)
    {
        this.belongsTo = newOwner
    }

    fun sendGift(to : User)
    {
        this.setOwner(to)
        this.getOwner().modifyHappiness(2)
        this.getOwner().modifyViolence(-2)
        to.modifyHappiness(2)
        to.modifyViolence(-2)
    }
}