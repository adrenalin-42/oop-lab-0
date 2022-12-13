package model

private val setOfServerNames = listOf(
    "MedForward",
    "Internetse",
    "iTransact Ltd",
    "Cergis Software",
    "WaveFX",
    "Magnetic Creative Services",
    "Lamb Creative Marketing",
    "Rise Hosting",
    "Psionic-Studios",
    "Digital-Dreams",
    "Webdigi",
    "Docklands Cloud Hosting",
    "Bluedata",
    "HostToast",
    "Ignite",
    "Akendi",
    "Atomic Design Nashville",
    "Netscan",
    "Elpro Hosting Company",
    "Hosting Karma",
    "Lime Creative",
    "Just web Hosting",
    "Byter",
    "QualiSpace",
    "NIMBLER",
    "Speak Creative Nashville",
    "CSL Web",
    "Selesti",
    "Scala Hosting LLC.",
    "Datapipe",
    "Codea software",
    "Fluent",
    "Dsgn One Web design",
    "Quantum Business Consulting",
    "Strange Bird Media",
    "Cabedge Design",
    "Bear Web Design",
    "The Positive Internet Company",
    "Alchemist Studios",
    "A2 Hosting",
    "End Point Corporation",
    "PulseHost",
    "CentralNic Ltd",
    "Hostingsource",
    "Blue Sky",
    "Hostrack",
    "Brady MillsGigaTux",
    "Tiny Shark",
    "Web Hosting Hub",
    "Vertical Web",
    "Netulip Hosting",
    "Adeo Digital Marketing Agency",
    "Avenue Design",
    "DevDigital",
    "GreenGeeks",
    "Grappo Hosting",
    "Ocean Hostia",
    "CloudSpace",
    "Evolve Design",
    "Creative Warehouse",
    "KD Web",
    "Golden Spiral Marketing",
    "Web",
    "G5 Media",
    "Social Media Marketing Agency",
    "Borne Digital",
    "Telstra",
    "Web Work",
    "Skyron",
    "Verasseti",
    "Atiba Hosting, LLC",
    "babyHost",
    "Chaos Design",
    "2idesign Ltd",
    "DePalma Studios",
    "Paramore Digital",
    "Interserver",
    "Iconic Digital",
    "Go4ace",
    "Digimax Dental Marketing",
    "Ember Media",
    "Level60 Consulting")
class Server(
    private val serverName: String = setOfServerNames.random(),
    private val history: ChatHistory = ChatHistory(),
    private val settings: Settings = Settings(),
): Universe() {
    private val users: MutableList<User> = mutableListOf()

    // initiate server
    init {
        println("Server ${this.serverName} has been created!")
    }

    // add user to server
    fun addConnection(user: User) {
        val userNickName = user.getNickName()
        users.add(user)
        println("$userNickName has successfully connected to $serverName.")
    }

    // remove user from server
    fun removeConnection(user: User) {
        val userNickName = user.getNickName()
        users.remove(user)
        println("$userNickName has successfully connected to $serverName.")
    }

    // add text log
    fun addTextLog(msg: TextMessage) {
        history.internalAddTextLog(msg)
    }

    // add voice log
    fun addVoiceLog(msg: VoiceMessage) {
        history.internalAddVoiceLog(msg)
    }

    // log photo log
    fun addPhotoLog(msg: PhotoMessage) {
        history.internalAddPhotoLog(msg)
    }

    // get save logs variable
    fun getSaveLogs(): Boolean {
        return (settings.internalGetSaveLogs())
    }

    // get server name
    fun getServerName(): String {
        return (this.serverName)
    }

    // get connected users
    fun getUsers(): MutableList<User> {
        return (this.users)
    }

    // get chat history
    fun getHistory(): ChatHistory {
        return (this.history)
    }

    // get chat history
    fun getSettings(): Settings {
        return (this.settings)
    }
}