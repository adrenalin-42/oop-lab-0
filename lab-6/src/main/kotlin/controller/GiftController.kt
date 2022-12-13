package controller

import model.Gift
import model.User
import view.GiftView

class GiftController(
    private var model : Gift,
    private val view : GiftView
) {

    // set new gift's owner
    fun setOwner(newOwner : User) {
        setOwner(newOwner)
    }

    // get gift's owner
    fun getOwner() : User {
        return (getOwner())
    }

    // set new gift's description
    fun setDescription(newDescription : String) {
        setDescription(newDescription)
    }

    // get gift's description
    fun getDescription() : String {
        return (getDescription())
    }

    fun updateView()
    {
        view.printContent(model.getOwner(), model.getGiftDescription())
    }
}