package plugantisocial

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class TrackinguserService {

    static transactional = true
    def sessionRegistry

    def serviceMethod() {

    }

    def getAllUsers()
    {
             sessionRegistry.allPrincipals
    }
}
