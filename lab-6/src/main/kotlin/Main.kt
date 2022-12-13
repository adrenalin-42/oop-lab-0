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

import controller.ServerController
import controller.ServerController.Aromatization.assignAllUsersToServer
import controller.UserController
import controller.UserController.Automate.runSimulation
import controller.UserController.Automate.viewAllUpdates
import model.*
import view.ServerView
import view.UserView
import kotlin.collections.HashMap
import kotlin.random.Random

fun main()
{
    val view : UserView = UserView()
    val userControllerList : MutableList<UserController> = UserController.getRandomUsers(5, view)

    val serverView : ServerView = ServerView()
    val serverController : MutableList<ServerController> = mutableListOf(ServerController(Server(), serverView))

    // initial data

    assignAllUsersToServer(userControllerList, serverController.first())

    runSimulation(50, userControllerList, serverController)
    viewAllUpdates(userControllerList)
    viewAllUpdates(userControllerList)
}