package model

class Role(private val roleName : String): Achievable()
{
    // print roles of user
    fun printRole()
    {
        println("The role is $roleName")
    }

    // get role name
    fun getRoleName(): String
    {
        return (roleName)
    }
}