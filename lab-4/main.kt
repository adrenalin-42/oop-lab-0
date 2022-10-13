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

// INTEFACES 

import kotlin.random.Random

interface printContent 
{
    fun printContent();
}   

// CLASSES

open class Universe()
{
    init
    {
        // in case you are wondering what the hell is going on here
        // these are lyrics from the "Big Bang Theory" series
        // println("Our whole universe was in a hot dense state,");
        // println("Then nearly fourteen billion years ago expansion started. Wait...");
        // println("The Earth began to cool,");
        // println("The autotrophs began to drool,");
        // println("Neanderthals developed tools,");
        // println("We built a wall (we built the pyramids),");
        // println("Math, science, history, unraveling the mystery,");
        // println("That all started with the big bang! (Bang!)");
    }
}

open class User(_nickName : String) : Universe(), printContent
{
    private val nickName = _nickName;
    private val gifts: MutableList<Gift> = mutableListOf();
    private val roles: MutableList<Role> = mutableListOf();

    init
    {
        println("User $nickName created!");
    }

    // send text message to server
    fun sendTextMessage(server : Server, textMessage : String)
    {
        val msg = TextMessage(this, textMessage);
        if (server.getSaveLogs())
        {
            server.addTextLog(msg);
        }
    }

    // send voice message to server
    fun sendVoiceMessage(server : Server, voiceMessage : String)
    {
        val msg = VoiceMessage(this, voiceMessage);
        if (server.getSaveLogs())
        {
            server.addVoiceLog(msg);
        }
    }

    // send photo message to server
    fun sendPhotoMessage(server : Server, photoMessage : String)
    {
        val msg = PhotoMessage(this, photoMessage);
        if (server.getSaveLogs())
        {
            server.addPhotoLog(msg);
        }
    }

    // print all content
    override fun printContent()
    {
        println("Nick Name: $nickName");
        println("Gifts: $gifts");
        println("Roles: $roles");
    }

    // add role to user
    fun addRole(_role : String) 
    {
        val newRole = Role(_role);

        roles.add(newRole);
    }

    // remove role from user
    fun removeRole(_role : String)
    {
        for (role in roles)
        {
            if (_role == role.getRoleName())
            {
                roles.remove(role);
                break;
            }
        }
    }

    // print roles of user
    fun printRoles()
    {
        for (role in roles)
        {
            println("$nickName has role $role");
        }
    }

    // create gift
    fun createGift (gift : String): Gift
    {
        val newGift = Gift(this, gift);
        gifts.add(newGift);

        return (newGift);
    }

    // send gifts
    fun sendGift(gift : Gift, sendTo : User)
    {
        val giftDescription = gift.getGiftDescription();

        gifts.remove(gift);
        sendTo.gifts.add(gift);
        println("$this has sent $sendTo $giftDescription");
    }

    // get user nickname
    fun getNickName(): String
    {
        return (nickName);
    }

    // assign user to server
    fun assignToServer(server : Server)
    {
        server.addConection(this);
    }
}

class Administrator(_user : String, _isAdmin : Boolean = false): User(_user)
{
    private var isAdmin : Boolean = _isAdmin;
    private val user : String = _user;

    // toggle admin status
    fun toggleAdmin()
    {
        isAdmin = !isAdmin;
    }

    // check if current user is admin
    fun checkAdmin()
    {
        if (isAdmin)
        {
            println("$user has SUPERPOWERS!");
        }
        else
        {
            println("$user is a peasant!");
        }
    }
}

open class Achivables(): Universe()
{

} 

class Role(_role : String): Achivables()
{
    private val roleName = _role;

    // print roles of user
    fun printRole()
    {
        println("The role is $roleName")
    }

    // get role name
    fun getRoleName(): String
    {
        return (roleName);
    }
}

class Server(_serverName : String, _history : ChatHistory, _settings : Settings): Universe()
{
    private val serverName : String =       _serverName;
    private val users : MutableList<User> = mutableListOf();
    private val history : ChatHistory =     _history;
    private val settings : Settings =       _settings;

    // initiate server
    init
    {
        println("Server $serverName has been created!");
    }

    // add user to server
    fun addConection(_user : User)
    {
        val userNickName = _user.getNickName();
        users.add(_user);
        println("$userNickName has succesfully connected to $serverName.")
    }

    // remove user from server
    fun removeConection(_user : User)
    {
        val userNickName = _user.getNickName();
        users.remove(_user);
        println("$userNickName has succesfully connected to $serverName.")
    }

    // add text log
    fun addTextLog(_msg : TextMessage)
    {
        history.internal_addTextLog(_msg);
    }

    // add voice log
    fun addVoiceLog(_msg : VoiceMessage)
    {
        history.internal_addVoiceLog(_msg);
    }

    // log photo log
    fun addPhotoLog(_msg : PhotoMessage)
    {
        history.internal_addPhotoLog(_msg);
    }

    // get save logs variable
    fun getSaveLogs(): Boolean
    {
        return (settings.internal_getSaveLogs());
    }

    // get server name
    fun getServerName(): String
    {
        return (this.serverName);
    }

    // print chat history
    fun printHistory()
    {
        history.printContent();
    }

    // print all connected users
    fun printUsers()
    {
        // for 
        println("$users")
    }
}

class Settings(_saveLogs : Boolean = true): Universe()
{
    private var saveLogs : Boolean = _saveLogs;

    // initate server, saving logs by default
    init
    {
        if (saveLogs == true)
        {
            println("Logs will be saved");
        }
        else
        {
            println("Logs will not be saved");
        }
    }

    // change saving logs into opposite
    fun toggleSaveLogs()
    {
        saveLogs = !saveLogs;
    }

    // get the logs
    fun internal_getSaveLogs(): Boolean
    {
        return (saveLogs);
    }
}

abstract class Message(user : User): Universe(), printContent
{
    internal val nickName : String = user.getNickName();

    // get user nickName
    fun getNickName() : String
    {
        return (nickName);
    }
}

class TextMessage(user : User, _textMessage : String): Message(user)
{
    private val textMessage : String = _textMessage;

    // a message has been sent
    init
    {
        println("$nickName says $textMessage");
    }

    // get message text
    fun getTextMessage(): String
    {
        return (textMessage);
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $textMessage");
    }
}

class VoiceMessage(user : User, _voiceMessage : String): Message(user)
{
    private val voiceMessage : String = _voiceMessage;

    // a voice message has been sent
    init
    {
        println("$nickName says via a voice message $voiceMessage");
    }

    // get voice message text
    fun getVoiceMessage(): String
    {
        return (voiceMessage);
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $voiceMessage");
    }
}

class PhotoMessage(user : User, _photoDescription : String): Message(user)
{
    private val photoDescription : String = _photoDescription;

    // a photo message has been sent
    init
    {
        println("$nickName sent a photo of $photoDescription");
    }

    // get voice message text
    fun getPhotoDescription(): String
    {
        return (photoDescription);
    }

    // print content
    override fun printContent()
    {
        println("$nickName says via a voice message $photoDescription");
    }
}

class Gift(_belongsTo : User, _gift : String): Achivables()
{
    private val giftDescription: String = _gift;
    private val belongsTo: User = _belongsTo;

    // a gift has been created
    init
    {
        println("A $giftDescription gift has been created by $belongsTo!");
    }

    fun getGiftDescription(): String
    {
        return (giftDescription);
    }
}

class ChatHistory(): Universe(), printContent
{
    private val history : MutableList<Any> = mutableListOf();

    // initate chat history
    init
    {
        println("Chat history started..")
    }

    // log text
    fun internal_addTextLog(_msg : TextMessage)
    {
        history.add(_msg);
    }

    // log voice
    fun internal_addVoiceLog(_msg : VoiceMessage)
    {
        history.add(_msg);
    }

    // log photo
    fun internal_addPhotoLog(_msg : PhotoMessage)
    {
        history.add(_msg);
    }

    // get text log
    fun getLog(): MutableList<Any>
    {
        return (history);
    }

    // check all history of chat
    override fun printContent()
    {
        for (msg in history)
        {
            if (!(msg is Message))
            {
                return
            }

            val nickName = msg.getNickName();

            if (msg is TextMessage)
            {
                val message = msg.getTextMessage();
                println("$nickName: $message");
            }
            else if (msg is VoiceMessage)
            {
                val message = msg.getVoiceMessage();
                println("$nickName (via voice): $message");
            }
            else if (msg is PhotoMessage)
            {
                val message = msg.getPhotoDescription();
                println("$nickName sent a photo of $message");
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
    "Jamison Schmidt");
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
    "Level60 Consulting");

    val setOfMessages = listOf(
    "It took him a month to finish the meal.",
    "They decided to plant an orchard of cotton candy.",
    "People generally approve of dogs eating cat food but not cats eating dog food.",
    "Stop waiting for exceptional things to just happen.",
    "As he entered the church he could hear the soft voice of someone whispering into a cell phone.",
    "The gloves protect my feet from excess work.",
    "Plans for this weekend include turning wine into water.",
    "Warm beer on a cold day isn't my idea of fun.",
    "He is good at eating pickles and telling women about his emotional problems.",
    "He was the type of guy who liked Christmas lights on his house in the middle of July.",
    "It was at that moment that he learned there are certain parts of the body that you should never Nair.",
    "It was always dangerous to drive with him since he insisted the safety cones were a slalom course.",
    "Garlic ice-cream was her favorite.",
    "He went on a whiskey diet and immediately lost three days.",
    "He took one look at what was under the table and noped the hell out of there.",
    "Pair your designer cowboy hat with scuba gear for a memorable occasion.",
    "I used to practice weaving with spaghetti three hours a day but stopped because I didn't want to die alone.",
    "I really want to go to work, but I am too sick to drive.",
    "There were a lot of paintings of monkeys waving bamboo sticks in the gallery.",
    "Instead of a bachelorette party",
    "The best key lime pie is still up for debate.",
    "It isn't difficult to do a handstand if you just stand on your hands.",
    "I never knew what hardship looked like until it started raining bowling balls.",
    "He stepped gingerly onto the bridge knowing that enchantment awaited on the other side.",
    "Being unacquainted with the chief raccoon was harming his prospects for promotion.",
    "Tom got a small piece of pie.",
    "The murder hornet was disappointed by the preconceived ideas people had of him.",
    "Gwen had her best sleep ever on her new bed of nails.",
    "I may struggle with geography, but I'm sure I'm somewhere around here.",
    "Thigh-high in the water, the fisherman’s hope for dinner soon turned to despair.",
    "He is no James Bond; his name is Roger Moore.",
    "I'm worried by the fact that my daughter looks to the local carpet seller as a role model.",
    "Watching the geriatric men’s softball team brought back memories of 3 yr olds playing t-ball.",
    "She was the type of girl that always burnt sugar to show she cared.",
    "Patricia loves the sound of nails strongly pressed against the chalkboard.",
    "I'm confused: when people ask me what's up, and I point, they groan.",
    "When he had to picnic on the beach, he purposely put sand in other people’s food.",
    "They looked up at the sky and saw a million stars.",
    "He shaved the peach to prove a point.",
    "I come from a tribe of head-hunters, so I will never need a shrink.",
    "Lucifer was surprised at the amount of life at Death Valley.",
    "Today we gathered moss for my uncle's wedding.",
    "Doris enjoyed tapping her nails on the table to annoy everyone.",
    "In the end, he realized he could see sound and hear words.",
    "Had he known what was going to happen, he would have never stepped into the shower.",
    "They wandered into a strange Tiki bar on the edge of the small beach town.",
    "There were three sphered rocks congregating in a cubed room.",
    "With a single flip of the coin, his life changed forever.",
    "The worst thing about being at the top of the career ladder is that there's a long way to fall.",
    )

    val setOfRoles = listOf(
    "electronic",
    "abundant",
    "tranquil",
    "annoyed",
    "selfish",
    "innocent",
    "additional",
    "lacking",
    "aboriginal",
    "arrogant",
    "whispering",
    "pregnant",
    "accidental",
    "ambiguous",
    "testy",
    "thirsty",
    "average",
    "incredible",
    "inner",
    "peaceful",
    "substantial",
    "staking",
    "puzzled",
    "assorted",
    "righteous",
    "ashamed",
    "stormy",
    "swift",
    "kindhearted",
    "equal",
    "frantic",
    "subdued",
    "marked",
    "best",
    "uneven",
    "global",
    "sedate",
    "kaput",
    "blushing",
    "silly",
    "faint",
    "wicked",
    "hellish",
    "wise",
    "empty",
    "homeless",
    "finicky",
    "long",
    "absurd",
    "smelly"
    )

    val setOfNouns = listOf(
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
    "homework"
    )

    val user1 = Administrator(setOfUserNames[Random.nextInt(setOfUserNames.size)]);

    val history = ChatHistory();
    val settings = Settings();
    val server1 = Server(setOfServerNames[Random.nextInt(setOfServerNames.size)], history, settings);
    // val ach1 = Gift(user1, "cute cat");
    user1.assignToServer(server1);
    user1.sendTextMessage(server1, setOfMessages[Random.nextInt(setOfMessages.size)]);


    // admin test
    user1.checkAdmin();

    user1.toggleAdmin();

    user1.checkAdmin();

    // chat history test
    server1.printHistory();

    // roles test

    user1.addRole(setOfRoles[Random.nextInt(setOfRoles.size)]);
    user1.addRole(setOfRoles[Random.nextInt(setOfRoles.size)]);

    user1.printRoles();

    // user1.removeRole("bibliophile");
    user1.printRoles();

    // different types of messages tests
    user1.sendPhotoMessage(server1, setOfNouns[Random.nextInt(setOfNouns.size)]);
    user1.sendVoiceMessage(server1, setOfMessages[Random.nextInt(setOfMessages.size)]);

    // chat history test again
    server1.printHistory();

    // gifts test
    val gift = user1.createGift(setOfNouns[Random.nextInt(setOfNouns.size)]);

    // second user test
    val user2 = User(setOfUserNames[Random.nextInt(setOfUserNames.size)]);
    user2.assignToServer(server1);

    user1.sendGift(gift, user2);
    // val univ = Universe();

    user1.printContent();
}