package webtours;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.FeederBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;


import static io.gatling.javaapi.core.CoreDsl.*;

public class CommonScenario {

    FeederBuilder<String> creds = csv("prop.csv").eager().circular();
    FeederBuilder<String> departCities = csv("departCities.csv").random().circular();
    FeederBuilder<String> arrivalCities = csv("arrivalCities.csv").random().circular();

    Action action = new Action();

     ChainBuilder login = group("login").on(
                exec(action.getMain())
                .exec(action.header())
                .exec(action.welcome())
                .exec(action.nav())
                .exec(action.login())
                .exec(action.loginNav())
                .exec(action.loginIntro()));

    ChainBuilder chooseFlight = group("choose flight").on(
            exec(action.getMain())
                    .exec(action.search())
                    .exec(action.searchFlight())
                    .exec(action.reservation())
                    .exec(action.chooseTickets())
                    .exec(action.choosePlane()));

    public ScenarioBuilder scn = scenario("BasicSimulation")
            .feed(creds)
            .feed(departCities)
            .feed(arrivalCities)
            .exec(login)
            .exec(chooseFlight)
            .exec(action.buyTicket())
            .exec(action.getMain());


}
