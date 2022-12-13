package controller

import model.*
import view.UserView
import kotlin.random.Random

class UserController(
    private var model : User,
    private val view : UserView,
    nrOfUsers : Int = 1,
) : Universe() {

    init {

    }

    // set user's nickname
    fun setNickName(newName : String)
    {
        model.setNickName(newName)
    }

    // get user's nickname
    fun getNickName() : String
    {
        return (model.getNickName())
    }

    // set user's happiness
    fun setHappiness(newHappiness : Int)
    {
        model.setHappiness(newHappiness)
    }

    // get user's happiness
    fun getHappiness() : Int
    {
        return (model.getHappiness())
    }

    // set user's happiness
    fun setViolence(newViolence : Int)
    {
        model.setViolence(newViolence)
    }

    // get user's violence
    fun getViolence() : Int
    {
        return (model.getViolence())
    }

    // set user's IQ
    fun setIq(newIq : Int)
    {
        model.setIq(newIq)
    }

    // get user's IQ
    fun getIq() : Int
    {
        return (model.getIq())
    }

    // set user's gifts
    fun setGifts(newGifts : MutableList<Gift>)
    {
        model.setGifts(newGifts)
    }

    // get user's gifts
    fun getGifts() : MutableList<Gift>
    {
        return (model.getGifts())
    }

    // set user's gifts
    fun setRoles(newRoles : MutableList<Role>)
    {
        model.setRoles(newRoles)
    }

    // get user's roles
    fun getRoles() : MutableList<Role>
    {
        return (model.getRoles())
    }

    // assign user to server
    fun assignToServer(server : Server)
    {
        model.assignToServer(server)
    }

    // get user
    fun getUser() : Any
    {
        return (model)
    }

    // add role to user
    fun addRole(tmpRole : String, happinessLevel: Int)
    {
        model.addRole(tmpRole, happinessLevel)
    }

    // remove role from user
    fun removeRole(tmpRole : Role)
    {
        model.removeRole(tmpRole)
    }

    // send text message to server
    fun sendTextMessage(server : Server, textMessage : String, impact : List<Int>)
    {
        model.sendTextMessage(server, textMessage, impact)
    }

    // send voice message to server
    fun sendVoiceMessage(server : Server, voiceMessage : String, impact : List<Int>)
    {
        model.sendVoiceMessage(server, voiceMessage, impact)
    }

    // send photo message to server
    fun sendPhotoMessage(server : Server, photoMessage : String, impact : List<Int>)
    {
        model.sendPhotoMessage(server, photoMessage, impact)
    }

    fun updateView()
    {
        view.printContent(model.getNickName(), model.getGifts(), model.getRoles(), model.getHappiness(), model.getViolence(), model.getIq())
    }

    companion object Automate {

        // view all updates
        fun viewAllUpdates(userController : MutableList<UserController>) {
            for (user in userController) {
                user.updateView()
            }
            readln()
        }

        // get random user
        fun getRandomUsers(nrOfUsers : Int, view: UserView) : MutableList<UserController>
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
            val tmp : MutableList<UserController> = mutableListOf()
            var i : Int = 0
            while (i < nrOfUsers) {
                if (Random.nextInt(0, 4) == 0) {
                    val tmpUser = Administrator(
                        user = setOfUserNames.random(),
                        isAdmin = true,
                        happiness = Random.nextInt(0, 100),
                        violence = Random.nextInt(0, 100),
                        iq = Random.nextInt(0, 100)
                    )
                    tmp.add(UserController(tmpUser, view))
                } else {
                    val tmpUser = User(
                        nickName = setOfUserNames.random(),
                        happiness = Random.nextInt(0, 100),
                        violence = Random.nextInt(0, 100),
                        iq = Random.nextInt(0, 100)
                    )
                    tmp.add(UserController(tmpUser, view))
                }
                i += 1
            }
            return (tmp)
        }

        fun runSimulation(steps : Int,
                          userController : MutableList<UserController>,
                          serverController : MutableList<ServerController> = mutableListOf()
        )
        {
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

            var j : Int = 0
            var i : Int = 0
            while (j < steps) {
                i = 0

                while (i < userController.size) {
                    if (userController[i].getHappiness() > 60 && Random.nextInt(0, 10) == 0) {
                        val tmpGift = Gift(userController[i].getUser() as User, setOfNouns.random())
                        tmpGift.sendGift(userController[Random.nextInt(0, userController.size)].getUser() as User)
                    }

                    if (userController[i].getHappiness() > 70 && userController[i].getUser() is Administrator) {
                        val tmpForRole = setOfRoles.keys.random()
                        userController[i].addRole(tmpForRole, setOfRoles[tmpForRole]!!)
                    }

                    if (userController[i].getHappiness() < 30 && userController[i].getUser() is Administrator) {
                        val randUser = userController[Random.nextInt(0, userController.size)].getRoles()
                        if (randUser.isNotEmpty())
                        {
                            userController[i].removeRole(randUser.random())
                        }
                    }

                    when (Random.nextInt(0, 3)) {
                        0 -> {
                            val tmpMsg = setOfMessages.keys.random()
                            userController[i].sendTextMessage(serverController[0].getServer(), tmpMsg, setOfMessages[tmpMsg]!!)
                        }
                        1 -> {
                            val tmpMsg = setOfMessages.keys.random()
                            userController[i].sendVoiceMessage(serverController[0].getServer(), tmpMsg, setOfMessages[tmpMsg]!!)
                        }
                        else -> {
                            val tmpMsg = setOfMessages.keys.random()
                            userController[i].sendPhotoMessage(serverController[0].getServer(), tmpMsg, setOfMessages[tmpMsg]!!)
                        }
                    }
                    i += 1
                }
                j += 1
            }
        }
    }
}