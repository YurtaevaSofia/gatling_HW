package simulations;

import io.gatling.javaapi.core.Simulation;
import webtours.CommonScenario;
import webtours.Perf;

import static io.gatling.javaapi.core.CoreDsl.*;

public class Reliability extends Simulation {

    CommonScenario commonScenario = new CommonScenario();
    Perf perf = new Perf();

    {
        setUp(
                commonScenario.scn.injectClosed(
                                constantConcurrentUsers(240).during(3000)
                        )
                        .protocols(perf.httpProtocol));
    }

}
