import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.web.authentication.session.ConcurrentSessionControlStrategy
import org.springframework.security.web.session.ConcurrentSessionFilter
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.codehaus.groovy.grails.plugins.springsecurity.SecurityFilterPosition

class PlugAntiSocialGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "1.3.7 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def loadAfter = ['spring-security-core']

    // TODO Fill in these fields
    def author = "Your name"
    def authorEmail = "lamarque.matthieu@gmail.com"
    def title = "Plugin summary/headline"
    def description = '''\\
Brief description of the plugin.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/plug-anti-social"

    def doWithWebDescriptor = { xml ->

        def listenerElements = xml.'listener'
        def lastListener = listenerElements[listenerElements.size() - 1]
        lastListener + {
            'listener' {
                'listener-class'('org.springframework.security.web.session.HttpSessionEventPublisher')
            }
        }
        // TODO Implement additions to web.xml (optional), this event occurs before 
    }

    def doWithSpring = {
        sessionRegistry(SessionRegistryImpl)

        sessionAuthenticationStrategy(ConcurrentSessionControlStrategy, ref('sessionRegistry')) {
            maximumSessions = -1
        }

        sessionFilter(ConcurrentSessionFilter) {
            sessionRegistry = ref('sessionRegistry')
        }
        // TODO Implement runtime spring config (optional)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
         SpringSecurityUtils.clientRegisterFilter("sessionFilter", SecurityFilterPosition.CONCURRENT_SESSION_FILTER)
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
