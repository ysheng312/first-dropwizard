package com.yaxuansheng.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.yaxuansheng.api.SayingRepresentation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by shengy on 1/19/16.
 *
 * Resource classes are used by multiple threads concurrently. In general, we recommend that resources be
 * stateless/immutable, but it’s important to keep the context in mind.
 *
 */
@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class MicroServiceResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public MicroServiceResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public SayingRepresentation sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, name.or(defaultName));
        return new SayingRepresentation(counter.incrementAndGet(), value);
    }
}
