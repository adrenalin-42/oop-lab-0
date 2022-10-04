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

open class User(_nickName : String) : Universe()
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
    private val user = _user;

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

class Server(_name : String): Universe()
{
    private val serverName = _name;
    private val users : MutableList<User> = mutableListOf();
    private val history = ChatHistory();
    private val settings = Settings();

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
        history.internal_printHistory();
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

open class Message(user : User): Universe()
{
    internal val nickName = user.getNickName();

    // get user nickName
    fun getNickName() : String
    {
        return (nickName);
    }
}

class TextMessage(user : User, _textMessage : String): Message(user)
{
    private val textMessage = _textMessage;

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
}

class VoiceMessage(user : User, _voiceMessage : String): Message(user)
{
    private val voiceMessage = _voiceMessage;

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
}

class PhotoMessage(user : User, _photoDescription : String): Message(user)
{
    private val photoDescription = _photoDescription;

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

class ChatHistory(): Universe()
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
    fun internal_printHistory()
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
    val user1 = Administrator("John");
    val server1 = Server("FAF-21X");
    val ach1 = Gift(user1, "cute cat");
    user1.assignToServer(server1);
    user1.sendTextMessage(server1, "Hello world!");


    // admin test
    user1.checkAdmin();

    user1.toggleAdmin();

    user1.checkAdmin();

    // chat history test
    server1.printHistory();

    // roles test

    user1.addRole("om nebun");
    user1.addRole("bibliophile");

    user1.printRoles();

    user1.removeRole("bibliophile");
    user1.printRoles();

    // different types of messages tests
    user1.sendPhotoMessage(server1, "cute cat");
    user1.sendVoiceMessage(server1, "hai la baut");

    // chat history test again
    server1.printHistory();

    // gifts test
    val gift = user1.createGift("caiet cu masinele");

    // second user test
    val user2 = User("Kolea");
    user2.assignToServer(server1);

    user1.sendGift(gift, user2);
    // val univ = Universe();
}