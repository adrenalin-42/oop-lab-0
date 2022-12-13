package view

import model.ChatHistory
import model.PrintContent
import model.Settings
import model.Universe

class ServerView(): Universe() {

    // print all content
    fun printContent(serverName: String,
                      history: ChatHistory,
                      settings: Settings,
    ) {
        println()
        println("----------[ SERVER $serverName ]----------")
        println("Gifts: $history")
        println("Roles: $settings")
        println("----------[ THE END ]----------")
        println()
    }
}