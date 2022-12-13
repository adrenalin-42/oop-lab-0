package model

class Settings(private var saveLogs: Boolean = true): Universe()
{
    // initiate server, saving logs by default
    init
    {
        if (this.saveLogs)
        {
            println("Logs will be saved")
        }
        else
        {
            println("Logs will not be saved")
        }
    }

    // change saving logs into opposite
    fun toggleSaveLogs()
    {
        saveLogs = !saveLogs
    }

    // get the logs
    fun internalGetSaveLogs(): Boolean
    {
        return (saveLogs)
    }
}