package org.morgade.grumbler.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Classe que registra os JAX-RS Resources e a classe de configuração do Jackson
 */
public class JaxRsApplication extends ResourceConfig {

    public JaxRsApplication() {
        packages("org.morgade.grumbler.rest.resource");
    }
}
