package glailton.io.github.travelcost.utils

class ApiEndpoint {
    var baseUrl: String = "http://10.0.2.2:5242/travelcost/"
        set(url) {
            field = url
            if (!url.endsWith("/")) {
                field += "/"
            }
        }
}