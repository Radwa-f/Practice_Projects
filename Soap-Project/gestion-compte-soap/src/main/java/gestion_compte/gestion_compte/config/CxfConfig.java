package gestion_compte.gestion_compte.config;

import gestion_compte.gestion_compte.ws.CompteSoapService;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
@AllArgsConstructor
public class CxfConfig {

    private CompteSoapService compteSoapService;
    private Bus bus;

    @Bean
    public EndpointImpl endpoint () {
        EndpointImpl endpoint = new EndpointImpl(bus, compteSoapService) ;
        endpoint.publish("/ws");
        return endpoint;
}
}
