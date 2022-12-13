package view

import model.User

class GiftView {
    // print all content
    fun printContent(belongsTo: User,
                     giftDescription: String,
    ) {
        println()
        println("----------[ BELONGS TO $belongsTo ]----------")
        println("Description: $giftDescription")
        println("----------[ THE END ]----------")
        println()
    }
}