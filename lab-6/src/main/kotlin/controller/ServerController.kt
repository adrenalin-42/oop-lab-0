package controller

import model.*
import view.ServerView

class ServerController(
    private var model : Server,
    private val view : ServerView
) : Universe() {

    // add user to server
    fun addConnection(user : User)
    {
        model.addConnection(user)
    }

    // remove user from server
    fun removeConnection(user : User)
    {
        model.removeConnection(user)
    }

    // add text log
    fun addTextLog(msg : TextMessage)
    {
        model.addTextLog(msg)
    }

    // add voice log
    fun addVoiceLog(msg : VoiceMessage)
    {
        model.addVoiceLog(msg)
    }

    // log photo log
    fun addPhotoLog(msg : PhotoMessage)
    {
        model.addPhotoLog(msg)
    }

    // get save logs variable
    fun getSaveLogs(): Boolean
    {
        return (model.getSaveLogs())
    }

    // get server name
    fun getServerName(): String
    {
        return (model.getServerName())
    }

    // get connected users
    fun getUsers(): MutableList<User>
    {
        return (model.getUsers())
    }

    // get chat history
    fun getHistory(): ChatHistory
    {
        return (model.getHistory())
    }

    // get chat history
    fun getSettings(): Settings
    {
        return (model.getSettings())
    }

    // get server
    fun getServer() : Server
    {
        return (model)
    }

    fun updateView()
    {
        view.printContent(model.getServerName(), model.getHistory(), model.getSettings())
    }

    companion object Aromatization {
        fun assignAllUsersToServer(userController: MutableList<UserController>, serverController: ServerController)
        {
            for (user in userController) {
                user.assignToServer(serverController.getServer())
            }
        }
    }
}