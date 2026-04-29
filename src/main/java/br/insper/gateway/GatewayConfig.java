package br.insper.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

        @Value("${services.clube}")
        private String clubeServiceUrl;

        @Value("${services.jogador}")
        private String jogadorServiceUrl;

        @Value("${services.jogos}")
        private String jogosServiceUrl;

        @Bean
        public RouteLocator routes(RouteLocatorBuilder builder, TimingGatewayFilterFactory timing) {
                return builder.routes()
                                .route("jogos", r -> r.path("/jogos/**")
                                                .filters(f -> f
                                                                .stripPrefix(1)
                                                                .filter(timing.apply(config(true, "jogos-route"))))
                                                .uri(jogosServiceUrl))
                                .route("clube", r -> r.path("/clube/**")
                                                .filters(f -> f
                                                                .filter(timing.apply(config(true, "clube-route"))))
                                                .uri(clubeServiceUrl))
                                .route("jogador", r -> r.path("/jogador/**")
                                                .filters(f -> f
                                                                .stripPrefix(1)
                                                                .filter(timing.apply(config(true, "jogador-route"))))
                                                .uri(jogadorServiceUrl))
                                .build();
        }

        private TimingGatewayFilterFactory.Config config(boolean enabled, String label) {
                TimingGatewayFilterFactory.Config config = new TimingGatewayFilterFactory.Config();
                config.setEnabled(enabled);
                config.setLabel(label);
                return config;
        }
}
