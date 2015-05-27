package com.novbank.ndp.kernel.core.serialization;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hppc.HppcModule;
import com.fasterxml.jackson.datatype.jdk7.Jdk7Module;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import io.dropwizard.jackson.*;

/**
 * A utility class for Jackson.
 *
 * Created by hp on 2015/5/26.
 */
public class Jackson {
    private Jackson() { /* singleton */ }
    /**
     * Creates a new {@link ObjectMapper} with Guava, Logback, and Joda Time support, as well as
     * support for {@link JsonSnakeCase}. Also includes all {@link Discoverable} interface implementations.
     */
    public static ObjectMapper newInstance() {
        final ObjectMapper mapper = new ObjectMapper();

        return configure(mapper);
    }

    /**
     * Creates a new {@link ObjectMapper} with a custom {@link com.fasterxml.jackson.core.JsonFactory}
     * with Guava, Logback, and Joda Time support, as well as support for {@link JsonSnakeCase}.
     * Also includes all {@link Discoverable} interface implementations.
     *
     * @param jsonFactory instance of {@link com.fasterxml.jackson.core.JsonFactory} to use
     *                    for the created {@link com.fasterxml.jackson.databind.ObjectMapper} instance.
     */
    public static ObjectMapper newInstance(JsonFactory jsonFactory) {
        final ObjectMapper mapper = new ObjectMapper(jsonFactory);

        return configure(mapper);
    }

    private static ObjectMapper configure(ObjectMapper mapper) {
        mapper.registerModule(new Hibernate4Module());
        mapper.registerModule(new HppcModule());
        mapper.registerModule(new Jdk7Module());
        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(new JodaModule());
        mapper.registerModule(new GuavaModule());
        mapper.registerModule(new GuavaExtrasModule());
        mapper.registerModule(new FuzzyEnumModule());
        mapper.registerModule(new LogbackModule());
        mapper.registerModule(new AfterburnerModule());
        mapper.registerModule(new AvroModule());
        mapper.setPropertyNamingStrategy(new AnnotationSensitivePropertyNamingStrategy());
        mapper.setSubtypeResolver(new DiscoverableSubtypeResolver());
        return mapper;
    }
}
