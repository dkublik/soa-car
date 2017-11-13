package pl.dk.soa.service;

import com.github.tomakehurst.wiremock.common.ClasspathFileSource;
import org.springframework.stereotype.Service;
import com.github.tomakehurst.wiremock.WireMockServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@Service
class WireMockServerRunner {

    static WireMockServer server;

    @PostConstruct
    void postContruct() {
        server = new WireMockServer(
                options()
                        .port(8181)
                        .fileSource(new ClasspathFileSource("."))
        );
        server.start();
    }

    @PreDestroy
    void predestroy() {
        // WireMock.saveAllMappings();
        server.stop();
    }

}
