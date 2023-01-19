package simulations;

import io.gatling.javaapi.core.Simulation;
import webtours.CommonScenario;
import webtours.Perf;

import static io.gatling.javaapi.core.CoreDsl.incrementConcurrentUsers;
import static io.gatling.javaapi.core.CoreDsl.incrementUsersPerSec;

public class MaxPerformance extends Simulation {

    CommonScenario commonScenario = new CommonScenario();
    Perf perf = new Perf();

    {
        setUp(
                commonScenario.scn.injectClosed(
                                incrementConcurrentUsers(10)
                                .times(40)
                                .eachLevelLasting(10)
                                .separatedByRampsLasting(10)
                                .startingFrom(0)
                )
                        .protocols(perf.httpProtocol));
    }


}
