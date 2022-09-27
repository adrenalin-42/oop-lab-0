// Discord

// Server
// User
// Message
// Settings
// ChatHistory
// Administrator
// Roles
// Voice messages
// Photo
// Gifts

class User(_nickName : String)
{
    private val nickName = _nickName;
    private val admin = Administrator(this);
    private val roles = Roles(this);
    private val gifts : MutableList<Gift> = mutableListOf();

    init
    {
        println("User $nickName created!");
    }

    // send message to server
    fun sendMessage(server : Server, message : String)
    {
        val msg = Message(this, message);
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

    // check admin
    fun checkAdmin()
    {
        admin.checkAdmin();
    }

    // toggle admin
    fun toggleAdmin()
    {
        admin.toggleAdmin();
    }

    // add role to user
    fun addRole(_role : String) 
    {
        roles.addRole(_role);
    }

    // remove role from user
    fun removeRole(_role : String)
    {
        roles.removeRole(_role);
    }

    // print roles of user
    fun printRoles()
    {
        roles.printRoles();
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

class Administrator(_user : User, _isAdmin : Boolean = false)
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

class Roles(_user : User)
{
    private val roles : MutableList<String> = mutableListOf();
    private val user = _user;

    // add role to user
    fun addRole(_role : String) 
    {
        roles.add(_role);
    }

    // remove role from user
    fun removeRole(_role : String)
    {
        roles.remove(_role);
    }

    // print roles of user
    fun printRoles()
    {
        val nickName = user.getNickName();

        for (role in roles)
        {
            println("$nickName has roles $role")
        }
    }
}

class Server(_name : String)
{
    private val name = _name;
    private val users : MutableList<User> = mutableListOf();
    private val history = ChatHistory();
    private val settings = Settings();

    // initiate server
    init
    {
        println("Server $name has been created!");
    }

    // add user to server
    fun addConection(_user : User)
    {
        val userNickName = _user.getNickName();
        users.add(_user);
        println("$userNickName has succesfully connected to $name.")
    }

    // add text log
    fun addTextLog(_msg : Message)
    {
        history.addTextLog(_msg);
    }

    // add voice log
    fun addVoiceLog(_msg : VoiceMessage)
    {
        history.addVoiceLog(_msg);
    }

    // log photo log
    fun addPhotoLog(_msg : PhotoMessage)
    {
        history.addPhotoLog(_msg);
    }

    // get save logs
    fun getSaveLogs(): Boolean
    {
        return (settings.getSaveLogs());
    }

    // print chat history
    fun printHistory()
    {
        history.printHistory();
    }

    // print all connected users
    fun printUsers()
    {
        // for 
        println("$users")
    }
}

class Settings(_saveLogs : Boolean = true)
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

    fun getSaveLogs(): Boolean
    {
        return (saveLogs);
    }
}

class Message(_user : User, _message : String)
{
    private val message = _message;
    private val nickName = _user.getNickName();

    // a message has been sent
    init
    {
        println("$nickName says $message");
    }

    // get message text
    fun getMessage(): String
    {
        return (message);
    }

    // get user nickname
    fun getNickName(): String
    {
        return (nickName);
    }
}

class VoiceMessage(_user : User, _voiceMessage : String)
{
    private val voiceMessage = _voiceMessage;
    private val nickName = _user.getNickName();

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

    // get user nickname
    fun getNickName(): String
    {
        return (nickName);
    }
}

class PhotoMessage(_user : User, _photoDescription : String)
{
    private val photoDescription = _photoDescription;
    private val nickName = _user.getNickName();

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

    // get user nickname
    fun getNickName(): String
    {
        return (nickName);
    }
}

class Gift(_belongsTo : User, _gift : String)
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

class ChatHistory()
{
    private val history : MutableList<Any> = mutableListOf();

    // initate chat history
    init
    {
        println("Chat history started..")
    }

    // log text
    fun addTextLog(_msg : Message)
    {
        history.add(_msg);
    }

    // log voice
    fun addVoiceLog(_msg : VoiceMessage)
    {
        history.add(_msg);
    }

    // log photo
    fun addPhotoLog(_msg : PhotoMessage)
    {
        history.add(_msg);
    }

    // get text log
    fun getLog(): MutableList<Any>
    {
        return (history);
    }

    // check all history of chat
    fun printHistory()
    {
        for (msg in history)
        {
            if (msg is Message)
            {
                val message = msg.getMessage();
                val nickName = msg.getNickName();
                println("$nickName: $message");
            }
            else if (msg is VoiceMessage)
            {
                val message = msg.getVoiceMessage();
                val nickName = msg.getNickName();
                println("$nickName (via voice): $message");
            }
            else if (msg is PhotoMessage)
            {
                val message = msg.getPhotoDescription();
                val nickName = msg.getNickName();
                println("$nickName sent a photo of $message");
            }
        }
    }
}

fun main()
{
    val user1 = User("John");
    val server1 = Server("FAF-21X");
    user1.assignToServer(server1);
    user1.sendMessage(server1, "Hello world!");


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
}