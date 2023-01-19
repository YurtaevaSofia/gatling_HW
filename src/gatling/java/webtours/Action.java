package webtours;

import io.gatling.javaapi.http.HttpRequestActionBuilder;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class Action {


    public HttpRequestActionBuilder getMain() {
        return http("get main page")
                .get("/webtours")
                .check(status().is(200));
    }

    public HttpRequestActionBuilder header() {
        return http("get header")
                .get("/webtours/header.html")
                .check(status().is(200));
    }

    public HttpRequestActionBuilder welcome() {
        return http("get welcome")
                .get("/cgi-bin/welcome.pl")
                .queryParam("signOff", "true")
                .check(status().is(200));
    }

    public HttpRequestActionBuilder nav() {
        return http("get nav")
                .get("/cgi-bin/nav.pl?in=home")
                .check(status().is(200))
                .check(
                        css("body > form > input[type=hidden]:nth-child(1)", "value").saveAs("userSession"));
    }



    public HttpRequestActionBuilder login() {
        return http("login")
                .post("/cgi-bin/login.pl")
                .queryParam("username", "#{username}")
                .queryParam("password", "#{password}")
//                .queryParam("login.x", 49)
//                .queryParam("login.y", 6)
                .queryParam("JSFormSubmit", "off")
                .queryParam("userSession", "#{userSession}")
                .check(status().is(200));
    }


    public HttpRequestActionBuilder loginNav() {
        return http("login nav")
                .get("/cgi-bin/nav.pl")
                .queryParam("page", "menu")
                .queryParam("in", "home")
                .check(status().is(200));
                //.check(regex("Welcome").exists());
    }

    public HttpRequestActionBuilder loginIntro() {
        return http("login intro")
                .get("/cgi-bin/login.pl")
                .queryParam("intro", true)
                .check(status().is(200))
                .check(regex("Welcome").exists());
    }

    public HttpRequestActionBuilder search() {
        return http("welcome search")
                .get("/cgi-bin/welcome.pl")
                .queryParam("page", "search")
                .check(status().is(200));
    }

    public HttpRequestActionBuilder searchFlight() {
        return http("search flight")
                .get("/cgi-bin/nav.pl")
                .queryParam("page", "menu")
                .queryParam("if", "flights")
                .check(status().is(200));
    }

    public HttpRequestActionBuilder reservation() {
        return http("reservations welcome page")
                .get("/cgi-bin/reservations.pl")
                .queryParam("page", "welcome")
                .check(status().is(200))
                .check(regex("Find Flight").exists());
    }

    public HttpRequestActionBuilder chooseTickets() {
        return http("choose cities")
                .post("/cgi-bin/reservations.pl?findFlights.x=60&findFlights.y=9&.cgifields=roundtrip&.cgifields=seatType&.cgifields=seatPref")
                .queryParam("advanceDiscount", 0)
                .queryParam("depart", "#{departCity}")
                .queryParam("arrive", "#{arrivalCity}")
                .queryParam("departDate", "03/17/2023")
                .queryParam("returnDate", "03/25/2023")
                .queryParam("numPassengers", 1)
                .queryParam("seatPref", "None")
                .queryParam("seatType", "Coach")
                .check(status().is(200))
                //.check(regex("Flight departing").exists())
                ;
    }

    public HttpRequestActionBuilder choosePlane() {
        return http("choose plane")
                .post("/cgi-bin/reservations.pl?reserveFlights.x=34&reserveFlights.y=10")
                .queryParam("advanceDiscount", 0)
                .queryParam("outboundFlight", "620;586;03/17/2023")
                .queryParam("numPassengers", 1)
                .queryParam("seatPref", "None")
                .check(status().is(200))
                //.check(regex("Payment Details").exists())
                ;
    }

    public HttpRequestActionBuilder buyTicket() {
        return http("buy tickets")
                .post("/cgi-bin/reservations.pl?JSFormSubmit=off&buyFlights.x=53&buyFlights.y=14&.cgifields=saveCC")
                .queryParam("firstName", "Sofia")
                .queryParam("lastName", "Yurtaeva")
                .queryParam("address1", "Teply Stan 26")
                .queryParam("address2", "Moscow 111118")
                .queryParam("pass1", "Sofia Yurtaeva")
                .queryParam("oldCCOption", "")
                .queryParam("creditCard", "")
                .queryParam("expDate", "")
                .queryParam("seatType", "Coach")
                .queryParam("advanceDiscount", 0)
                .queryParam("outboundFlight", "620;586;03/17/2023")
                .queryParam("numPassengers", 1)
                .queryParam("seatPref", "None")
                .queryParam("returnFlight", "")
                .check(status().is(200))
                //.check(regex("Invoice").exists())
            ;
    }

}
