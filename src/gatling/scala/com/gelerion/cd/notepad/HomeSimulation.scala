package com.gelerion.cd.notepad

import io.gatling.core.Predef._
import io.gatling.http.Predef._

/**
  * Created by denis.shuvalov on 06/08/2018.
  */
class HomeSimulation extends Simulation {

  val httpConf = http
    .baseURL(System.getProperty("notepadUrl", "http://localhost:8080"))
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Should List Notes")
    .exec(http("request")
      .get("/"))

  setUp(scn.inject(atOnceUsers(100))
    .protocols(httpConf))
    .assertions(global.failedRequests.percent.is(0))

  //If for example you want to ensure that the maximum response time allowed is 3s, then you could
  //just include the assertion global.responseTime.max.lt(3000) to the assertions.
}
