// Discord

// Server
// User
// Message
// Settings
// ChatHistory
// Administrator
// Role
// Voice messages
// Photo
// Achievements
// Gifts


import kotlin.collections.HashMap

// INTERFACES
interface PrintContent
{
    fun printContent()
}

// CLASSES

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

open class User(private val nickName: String,
                private var happiness : Int = 50,
                private var violence : Int = 50,
                private var iq : Int = 50,
    ) : Universe(), PrintContent
{
    private val gifts: MutableList<Gift> = mutableListOf()
    private val roles: MutableList<Role> = mutableListOf()

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
        if (iq < 50)
        {

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

    // print all content
    override fun printContent()
    {
        println()
        println("----------[ USER $nickName ]----------")
        println("Gifts: ${this.gifts}")
        println("Roles: ${this.roles}")
        println("Happiness: ${this.happiness}")
        println("Violence: ${this.violence}")
        println("IQ: ${this.iq}")
        println("----------[ THE END ]----------")
        println()
    }

    // add role to user
    fun addRole(tmpRole : String, happinessLevel: Int)
    {
        val newRole = Role(tmpRole)
        happiness += happinessLevel

        roles.add(newRole)
    }

    // remove role from user
    fun removeRole(tmpRole : String)
    {
        for (role in roles)
        {
            if (tmpRole == role.getRoleName())
            {
                roles.remove(role)
                break
            }
        }
    }

    // print roles of user
    fun printRoles()
    {
        for (role in roles)
        {
            println("$nickName has role $role")
        }
    }

    // create gift
    fun createGift (gift : String): Gift
    {
        val newGift = Gift(this, gift)
        this.modifyHappiness(5)
        this.modifyViolence(-10)
        this.modifyIQ(5)
        gifts.add(newGift)

        return (newGift)
    }

    // send gifts
    fun sendGift(gift : Gift, sendTo : User)
    {
        val giftDescription = gift.getGiftDescription()

        this.modifyHappiness(10)
        this.modifyViolence(-10)
        sendTo.modifyHappiness(10)
        sendTo.modifyViolence(-10)

        gifts.remove(gift)
        sendTo.gifts.add(gift)
        println("$this has sent $sendTo $giftDescription")
    }

    // get user nickname
    fun getNickName(): String
    {
        return (this.nickName)
    }

    // get user gifts
    fun getGifts(): MutableList<Gift>
    {
        return (this.gifts)
    }

    // get user roles
    fun getRoles(): MutableList<Role>
    {
        return (this.roles)
    }

    // get user happiness
    fun getHappiness(): Int
    {
        return (this.happiness)
    }

    // get user violence
    fun getViolence(): Int
    {
        return (this.violence)
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
    protected fun modifyHappiness(happinessLevel: Int) {
        this.happiness += happinessLevel
        if (this.happiness < 0)
            this.happiness = 0
        if (this.happiness > 100)
            this.happiness = 100
    }

    protected fun modifyViolence(violenceLevel: Int) {
        this.violence += violenceLevel
        if (this.violence < 0)
            this.violence = 0
        if (this.violence > 100)
            this.violence = 100
    }

    protected fun modifyIQ(iqLevel: Int) {
        this.iq += iqLevel
        if (this.iq < 0)
            this.iq = 0
        if (this.iq > 100)
            this.iq = 100
    }
}

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

open class Achievable(): Universe()
{

}

class Role(private val roleName : String): Achievable()
{
    // print roles of user
    fun printRole()
    {
        println("The role is $roleName")
    }

    // get role name
    fun getRoleName(): String
    {
        return (roleName)
    }
}

class Server(
    private val serverName: String,
    private val history: ChatHistory,
    private val settings: Settings,
): Universe()
{
    private val users : MutableList<User> = mutableListOf()

    // initiate server
    init
    {
        println("Server ${this.serverName} has been created!")
    }

    // add user to server
    fun addConnection(user : User)
    {
        val userNickName = user.getNickName()
        users.add(user)
        println("$userNickName has successfully connected to $serverName.")
    }

    // remove user from server
    fun removeConnection(user : User)
    {
        val userNickName = user.getNickName()
        users.remove(user)
        println("$userNickName has successfully connected to $serverName.")
    }

    // add text log
    fun addTextLog(msg : TextMessage)
    {
        history.internalAddTextLog(msg)
    }

    // add voice log
    fun addVoiceLog(msg : VoiceMessage)
    {
        history.internalAddVoiceLog(msg)
    }

    // log photo log
    fun addPhotoLog(msg : PhotoMessage)
    {
        history.internalAddPhotoLog(msg)
    }

    // get save logs variable
    fun getSaveLogs(): Boolean
    {
        return (settings.internalGetSaveLogs())
    }

    // get server name
    fun getServerName(): String
    {
        return (this.serverName)
    }

    // get connected users
    fun getUsers(): MutableList<User>
    {
        return (this.users)
    }

    // print chat history
    fun printHistory()
    {
        history.printContent()
    }

    // print all connected users
    fun printUsers()
    {
        // for
        println("$users")
    }
}

class Settings(private var saveLogs: Boolean = true): Universe()
{
    // initiate server, saving logs by default
    init
    {
        if (this.saveLogs)
        {
            println("Logs will be saved")
        }
        else
        {
            println("Logs will not be saved")
        }
    }

    // change saving logs into opposite
    fun toggleSaveLogs()
    {
        saveLogs = !saveLogs
    }

    // get the logs
    fun internalGetSaveLogs(): Boolean
    {
        return (saveLogs)
    }
}

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

class Gift(private val belongsTo: User,
           private val gift : String,
): Achievable()
{
    // a gift has been created
    init
    {
        println("A $gift gift has been created by ${this.belongsTo}!")
    }

    fun getGiftDescription(): String
    {
        return (gift)
    }
}

class ChatHistory(): Universe(), PrintContent
{
    private val history : MutableList<Any> = mutableListOf()

    // initate chat history
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

fun main()
{
    val setOfUserNames = listOf(
        "Jason Wolf",
        "Rey Riggs",
        "Christian Holder",
        "Lanny Horton",
        "Wilton Lynn",
        "Jerald Franco",
        "Jacquelyn Rodriguez",
        "Ruth Buchanan",
        "Ruben Hicks",
        "Joesph Schmitt",
        "Garland Cole",
        "Janine Doyle",
        "Odessa Madden",
        "Crystal Parker",
        "Tyrone Salinas",
        "Kendall Ramos",
        "Gail Juarez",
        "Cordell Dennis",
        "Dorothea Frederick",
        "Valerie Guzman",
        "Lenore Kline",
        "Helen Nielsen",
        "Peter Hancock",
        "Corrine Yates",
        "Deshawn Le",
        "Liz Gilmore",
        "Lucia Blevins",
        "Sam Ellis",
        "Diana Spence",
        "Barry Grimes",
        "Leonardo Rios",
        "Gerald Bonilla",
        "Milagros Warner",
        "Jeannine Petty",
        "Lupe Turner",
        "Amanda Wilcox",
        "Raquel Duarte",
        "Sonny Prince",
        "Rico Carlson",
        "Forrest Wright",
        "Marcos Wilkerson",
        "Drew Mahoney",
        "Ross Kim",
        "Minnie Kramer",
        "Evangelina Howell",
        "Darla Lang",
        "Hong Hooper",
        "Osvaldo Rivera",
        "Celina Lucero",
        "Jamison Schmidt")
    val setOfServerNames = listOf(
        "MedForward",
        "Internetse",
        "iTransact Ltd",
        "Cergis Software",
        "WaveFX",
        "Magnetic Creative Services",
        "Lamb Creative Marketing",
        "Rise Hosting",
        "Psionic-Studios",
        "Digital-Dreams",
        "Webdigi",
        "Docklands Cloud Hosting",
        "Bluedata",
        "HostToast",
        "Ignite",
        "Akendi",
        "Atomic Design Nashville",
        "Netscan",
        "Elpro Hosting Company",
        "Hosting Karma",
        "Lime Creative",
        "Just web Hosting",
        "Byter",
        "QualiSpace",
        "NIMBLER",
        "Speak Creative Nashville",
        "CSL Web",
        "Selesti",
        "Scala Hosting LLC.",
        "Datapipe",
        "Codea software",
        "Fluent",
        "Dsgn One Web design",
        "Quantum Business Consulting",
        "Strange Bird Media",
        "Cabedge Design",
        "Bear Web Design",
        "The Positive Internet Company",
        "Alchemist Studios",
        "A2 Hosting",
        "End Point Corporation",
        "PulseHost",
        "CentralNic Ltd",
        "Hostingsource",
        "Blue Sky",
        "Hostrack",
        "Brady MillsGigaTux",
        "Tiny Shark",
        "Web Hosting Hub",
        "Vertical Web",
        "Netulip Hosting",
        "Adeo Digital Marketing Agency",
        "Avenue Design",
        "DevDigital",
        "GreenGeeks",
        "Grappo Hosting",
        "Ocean Hostia",
        "CloudSpace",
        "Evolve Design",
        "Creative Warehouse",
        "KD Web",
        "Golden Spiral Marketing",
        "Web",
        "G5 Media",
        "Social Media Marketing Agency",
        "Borne Digital",
        "Telstra",
        "Web Work",
        "Skyron",
        "Verasseti",
        "Atiba Hosting, LLC",
        "babyHost",
        "Chaos Design",
        "2idesign Ltd",
        "DePalma Studios",
        "Paramore Digital",
        "Interserver",
        "Iconic Digital",
        "Go4ace",
        "Digimax Dental Marketing",
        "Ember Media",
        "Level60 Consulting")

    // every message has an impact on happiness, violence and iq on the users connected to the server
    val setOfMessages : HashMap<String, List<Int>> = hashMapOf(
        "It took him a month to finish the meal." to listOf(1, 0, -1),
        "They decided to plant an orchard of cotton candy." to listOf(2, -1, 1),
        "People generally approve of dogs eating cat food but not cats eating dog food." to listOf(1, 0, -1),
        "Stop waiting for exceptional things to just happen." to listOf(-1, 1, 3),
        "As he entered the church he could hear the soft voice of someone whispering into a cell phone." to listOf(-1, 1, 0),
        "The gloves protect my feet from excess work." to listOf(0, 1, -1),
        "Plans for this weekend include turning wine into water." to listOf(2, -1, -2),
        "Warm beer on a cold day isn't my idea of fun." to listOf(-2, 2, 1),
        "He is good at eating pickles and telling women about his emotional problems." to listOf(-2, -1, 0),
        "He was the type of guy who liked Christmas lights on his house in the middle of July." to listOf(1, -1, -2),
        "It was at that moment that he learned there are certain parts of the body that you should never Nair." to listOf(0, 0, 1),
        "It was always dangerous to drive with him since he insisted the safety cones were a slalom course." to listOf(-2, 0, -2),
        "Garlic ice-cream was her favorite." to listOf(2, -1, -1),
        "He went on a whiskey diet and immediately lost three days." to listOf(3, -2, 2),
        "He took one look at what was under the table and noped the hell out of there." to listOf(1, 0, 1),
        "Pair your designer cowboy hat with scuba gear for a memorable occasion." to listOf(-1, 0, -2),
        "I used to practice weaving with spaghetti three hours a day but stopped because I didn't want to die alone." to listOf(2, 0, -2),
        "I really want to go to work, but I am too sick to drive." to listOf(0, 1, -1),
        "There were a lot of paintings of monkeys waving bamboo sticks in the gallery." to listOf(2, -1, 2),
        "Instead of a bachelorette party" to listOf(0, -1, -2),
        "The best key lime pie is still up for debate." to listOf(-1, 2, -1),
        "It isn't difficult to do a handstand if you just stand on your hands." to listOf(-3, 0, -2),
        "I never knew what hardship looked like until it started raining bowling balls." to listOf(2, -1, -1),
        "He stepped gingerly onto the bridge knowing that enchantment awaited on the other side." to listOf(1, -1, -1),
        "Being unacquainted with the chief raccoon was harming his prospects for promotion." to listOf(-1, 1, -1),
        "Tom got a small piece of pie." to listOf(-1, 1, 0),
        "The murder hornet was disappointed by the preconceived ideas people had of him." to listOf(2, -1, -2),
        "Gwen had her best sleep ever on her new bed of nails." to listOf(3, 1, -1),
        "I may struggle with geography, but I'm sure I'm somewhere around here." to listOf(3, -1, -2),
        "Thigh-high in the water, the fisherman’s hope for dinner soon turned to despair." to listOf(2, -1, -1),
        "He is no James Bond his name is Roger Moore." to listOf(3, -1, 1),
        "I'm worried by the fact that my daughter looks to the local carpet seller as a role model." to listOf(-1, 0, -1),
        "Watching the geriatric men’s softball team brought back memories of 3 yr olds playing t-ball." to listOf(3, -1, 1),
        "She was the type of girl that always burnt sugar to show she cared." to listOf(-2, 2, -2),
        "Patricia loves the sound of nails strongly pressed against the chalkboard." to listOf(-3, 4, -3),
        "I'm confused: when people ask me what's up, and I point, they groan." to listOf(1, -2, -3),
        "When he had to picnic on the beach, he purposely put sand in other people’s food." to listOf(-3, 3, -2),
        "They looked up at the sky and saw a million stars." to listOf(3, -2, 3),
        "He shaved the peach to prove a point." to listOf(2, 0, -3),
        "I come from a tribe of head-hunters, so I will never need a shrink." to listOf(1, -1, -2),
        "Lucifer was surprised at the amount of life at Death Valley." to listOf(2, -1, 2),
        "Today we gathered moss for my uncle's wedding." to listOf(3, -1, -2),
        "Doris enjoyed tapping her nails on the table to annoy everyone." to listOf(-4, 4, -3),
        "In the end, he realized he could see sound and hear words." to listOf(-1, 2, -2),
        "Had he known what was going to happen, he would have never stepped into the shower." to listOf(-2, -1, 2),
        "They wandered into a strange Tiki bar on the edge of the small beach town." to listOf(0, -1, -1),
        "There were three sphered rocks congregating in a cubed room." to listOf(1, -1, 2),
        "With a single flip of the coin, his life changed forever." to listOf(3, -1, 3),
        "The worst thing about being at the top of the career ladder is that there's a long way to fall." to listOf(-2, 1, 3),
    )

    val setOfRoles : HashMap<String, Int> = hashMapOf(
        "electronic" to 8,
        "abundant" to 2,
        "tranquil" to 3,
        "annoyed" to -3,
        "selfish" to -7,
        "innocent" to -3,
        "additional" to 3,
        "lacking" to -4,
        "aboriginal" to -8,
        "arrogant" to -10,
        "whispering" to -10,
        "pregnant" to -4,
        "accidental" to -7,
        "ambiguous" to 1,
        "testy" to 6,
        "thirsty" to 3,
        "average" to -4,
        "incredible" to 10,
        "inner" to 2,
        "peaceful" to 7,
        "substantial" to -10,
        "staking" to -6,
        "puzzled" to -2,
        "assorted" to -2,
        "righteous" to -5,
        "ashamed" to -5,
        "stormy" to 8,
        "swift" to 2,
        "kindhearted" to 6,
        "equal" to 7,
        "frantic" to 2,
        "subdued" to 3,
        "marked" to 4,
        "best" to 8,
        "uneven" to 2,
        "global" to 6,
        "sedate" to -1,
        "kaput" to 3,
        "blushing" to 7,
        "silly" to -2,
        "faint" to 1,
        "wicked" to 2,
        "hellish" to 4,
        "wise" to 10,
        "empty" to 2,
        "homeless" to -6,
        "finicky" to 3,
        "long" to 7,
        "absurd" to -7,
        "smelly" to -10,
    )

    val setOfNouns : List<String> = listOf(
        "management",
        "food",
        "dad",
        "selection",
        "difficulty",
        "refrigerator",
        "salad",
        "reception",
        "republic",
        "agency",
        "version",
        "security",
        "aspect",
        "tea",
        "elevator",
        "wedding",
        "description",
        "ability",
        "emphasis",
        "income",
        "farmer",
        "apartment",
        "establishment",
        "art",
        "shopping",
        "revenue",
        "loss",
        "clothes",
        "director",
        "potato",
        "impression",
        "satisfaction",
        "possibility",
        "enthusiasm",
        "suggestion",
        "nature",
        "driver",
        "birthday",
        "sir",
        "device",
        "responsibility",
        "bonus",
        "strategy",
        "solution",
        "sample",
        "lady",
        "bathroom",
        "knowledge",
        "investment",
        "homework",
    )

    // RANDOM MODE
    // USE THIS FOR A RANDOM BEHAVIOUR

    val universeCounter : Universe = Universe()
    val users : MutableList<User> = mutableListOf()
    val servers : MutableList<Server> = mutableListOf()
    users.add(Administrator(user = setOfUserNames[universeCounter.getCounter()], happiness = 10, violence = 90))

    val history : ChatHistory = ChatHistory()
    val settings : Settings = Settings()
    servers.add(Server(setOfServerNames[universeCounter.getCounter()], history, settings))
    users[0].assignToServer(servers[0])

    // second user test
    users.add(User(setOfUserNames[universeCounter.getCounter()]))
    users[1].assignToServer(servers[0])

    var tmpMsg = setOfMessages.keys.toList()[universeCounter.getCounter()]
    users[0].sendTextMessage(servers[0], tmpMsg, setOfMessages[tmpMsg]!!)

    tmpMsg = setOfMessages.keys.toList()[universeCounter.getCounter()]
    users[1].sendTextMessage(servers[0], tmpMsg, setOfMessages[tmpMsg]!!)

    tmpMsg = setOfMessages.keys.toList()[universeCounter.getCounter()]
    users[1].sendTextMessage(servers[0], tmpMsg, setOfMessages[tmpMsg]!!)


    // admin test
    (users[0] as Administrator).checkAdmin()

    (users[0] as Administrator).toggleAdmin()

    (users[0] as Administrator).checkAdmin()

    // chat history test
    servers[0].printHistory()

    // roles test
    var tmpForRole = setOfRoles.keys.toList()[universeCounter.getCounter()]
    users[0].addRole(tmpForRole, setOfRoles[tmpForRole]!!)
    tmpForRole = setOfRoles.keys.toList()[universeCounter.getCounter()]
    users[0].addRole(tmpForRole, setOfRoles[tmpForRole]!!)

    users[0].printRoles()

    // users[0].removeRole("bibliophile")
    users[0].printRoles()

    // different types of messages tests
    tmpMsg = setOfMessages.keys.toList()[universeCounter.getCounter()]
    users[0].sendPhotoMessage(servers[0], tmpMsg, setOfMessages[tmpMsg]!!)
    tmpMsg = setOfMessages.keys.toList()[universeCounter.getCounter()]
    users[0].sendVoiceMessage(servers[0], tmpMsg, setOfMessages[tmpMsg]!!)

    // chat history test again
    servers[0].printHistory()

    // gifts test
    val gift = users[0].createGift(setOfNouns[universeCounter.getCounter()])

    users[0].sendGift(gift, users[1])
    // val univ = Universe()

    for (user in users)
    {
        user.printContent()
    }

    print("hello".uppercase())

}