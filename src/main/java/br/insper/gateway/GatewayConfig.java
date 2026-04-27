package br.insper.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

        @Value("${services.clubes}")
        private String clubesServiceUrl;

        @Value("${services.jogadores}")
        private String jogadoresServiceUrl;

        @Value("${services.jogos}")
        private String jogosServiceUrl;

        @Bean
        public RouteLocator routes(RouteLocatorBuilder builder, TimingGatewayFilterFactory timing) {
                return builder.routes()
                                .route("jogos", r -> r.path("/jogos/**")
                                                .filters(f -> f
                                                                .filter(timing.apply(config(true, "jogos-route"))))
                                                .uri(jogosServiceUrl))
                                .route("clubes", r -> r.path("/clubes/**")
                                                .filters(f -> f
                                                                .filter(timing.apply(config(true, "clubes-route"))))
                                                .uri(clubesServiceUrl))
                                .route("jogadores", r -> r.path("/jogadores/**")
                                                .filters(f -> f
                                                                .filter(timing.apply(config(true, "connection-route"))))
                                                .uri(jogadoresServiceUrl))
                                .build();
        }

        private TimingGatewayFilterFactory.Config config(boolean enabled, String label) {
                TimingGatewayFilterFactory.Config config = new TimingGatewayFilterFactory.Config();
                config.setEnabled(enabled);
                config.setLabel(label);
                return config;
        }
}
