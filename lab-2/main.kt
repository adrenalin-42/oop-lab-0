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
    val nickName = _nickName;
    val admin = Administrator(this);
    val roles = Roles(this);
    val gifts : MutableList<Gift> = mutableListOf();

    init
    {
        println("User $nickName created!");
    }

    // send message to server
    fun sendMessage(server : Server, message : String)
    {
        val msg = Message(this, message);
        if (server.settings.saveLogs)
        {
            server.history.addTextLog(msg);
        }
    }

    // send voice message to server
    fun sendVoiceMessage(server : Server, voiceMessage : String)
    {
        val msg = VoiceMessage(this, voiceMessage);
        if (server.settings.saveLogs)
        {
            server.history.addVoiceLog(msg);
        }
    }

    // send photo message to server
    fun sendPhotoMessage(server : Server, photoMessage : String)
    {
        val msg = PhotoMessage(this, photoMessage);
        if (server.settings.saveLogs)
        {
            server.history.addPhotoLog(msg);
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
        val giftDescription = gift.gift;

        gifts.remove(gift);
        sendTo.gifts.add(gift);
        println("$this has sent $sendTo $giftDescription");
    }

    // assign user to server
    fun assignToServer(server : Server)
    {
        server.addConection(this);
    }
}

class Administrator(_user : User, _isAdmin : Boolean = false)
{
    var isAdmin : Boolean = _isAdmin;
    val user = _user;

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
    val roles : MutableList<String> = mutableListOf();
    val user = _user;

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
        val nickName = user.nickName;

        for (role in roles)
        {
            println("$nickName has roles $role")
        }
    }
}

class Server(_name : String)
{
    val name = _name;
    val users : MutableList<User> = mutableListOf();
    val history = ChatHistory();
    val settings = Settings();

    // initiate server
    init
    {
        println("Server $name has been created!");
    }

    // add user to server
    fun addConection(_user : User)
    {
        val userNickName = _user.nickName;
        users.add(_user);
        println("$userNickName has succesfully connected to $name.")
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
    var saveLogs : Boolean = _saveLogs;

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
}

class Message(_user : User, _message : String)
{
    val message = _message;
    val nickName = _user.nickName;

    // a message has been sent
    init
    {
        println("$nickName says $message");
    }    
}

class VoiceMessage(_user : User, _voiceMessage : String)
{
    val voiceMessage = _voiceMessage;
    val nickName = _user.nickName;

    // a voice message has been sent
    init
    {
        println("$nickName says via a voice message $voiceMessage");
    }
}

class PhotoMessage(_user : User, _photoDescription : String)
{
    val photoDescription = _photoDescription;
    val nickName = _user.nickName;

    // a photo message has been sent
    init
    {
        println("$nickName sent a photo of $photoDescription");
    }
}

class Gift(_belongsTo : User, _gift : String)
{
    val gift = _gift;
    val belongsTo = _belongsTo;

    // a gift has been created
    init
    {
        println("A $gift gift has been created by $belongsTo!");
    }
}

class ChatHistory()
{
    val history : MutableList<Any> = mutableListOf();

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

    // check all history of chat
    fun printHistory()
    {
        for (msg in history)
        {
            if (msg is Message)
            {
                val message = msg.message;
                val nickName = msg.nickName;
                println("$nickName: $message");
            }
            else if (msg is VoiceMessage)
            {
                val message = msg.voiceMessage;
                val nickName = msg.nickName;
                println("$nickName (via voice): $message");
            }
            else if (msg is PhotoMessage)
            {
                val message = msg.photoDescription;
                val nickName = msg.nickName;
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
    user1.admin.checkAdmin();

    user1.admin.toggleAdmin();

    user1.admin.checkAdmin();

    // chat history test
    server1.history.printHistory();

    // roles test
    user1.roles.addRole("om nebun");
    user1.roles.addRole("bibliophile");

    user1.roles.printRoles();

    user1.roles.removeRole("bibliophile");
    user1.roles.printRoles();

    // different types of messages tests
    user1.sendPhotoMessage(server1, "cute cat");
    user1.sendVoiceMessage(server1, "hai la baut");

    // chat history test again
    server1.history.printHistory();

    // gifts test
    val gift = user1.createGift("caiet cu masinele");

    // second user test
    val user2 = User("Kolea");
    user2.assignToServer(server1);

    user1.sendGift(gift, user2);
}