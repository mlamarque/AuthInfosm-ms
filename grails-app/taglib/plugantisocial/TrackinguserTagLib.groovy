package plugantisocial

class TrackinguserTagLib {

    def trackinguserService

    def usersConnected = { attrs ->

        //def principals = trackingUserService.getUserPrincipals()
        //println(principals)
        def principals = trackinguserService.getAllUsers()
        println(principals)
        out << "<ul><p> Connected users </p>"
        for (Object principal: principals) {
            println(principal.username)
            out << "<li>" + principal.username + "</li>"
        }
        out << "<ul>"
    }
}
