package simulations;

import io.gatling.javaapi.core.Simulation;
import webtours.CommonScenario;
import webtours.Perf;

import static io.gatling.javaapi.core.CoreDsl.atOnceUsers;
import static io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec;

public class Debug extends Simulation {

    CommonScenario commonScenario = new CommonScenario();
    Perf perf = new Perf();

    {
        setUp(
                commonScenario.scn.injectOpen(atOnceUsers(1))
                        .protocols(perf.httpProtocol));
    }
}
