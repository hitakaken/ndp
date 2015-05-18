package com.novbank.ndp.metrics;

import static com.codahale.metrics.MetricFilter.ALL;
import static java.lang.management.ManagementFactory.getPlatformMBeanServer;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.slf4j.LoggerFactory.getLogger;

import javax.management.MBeanServer;

import org.slf4j.Logger;

import com.codahale.metrics.JmxReporter;
import com.codahale.metrics.graphite.Graphite;
import com.codahale.metrics.graphite.GraphiteReporter;

/**
 * Helpers for making the upstream metrics reporters play nice with Springg
 *
 * Created by hp on 2015/5/18.
 */
public class ReporterFactory {
    private static final Logger LOGGER = getLogger(ReporterFactory.class);
    private RegistryService registryService = RegistryService.getInstance();

    /**
     * Start a new GraphiteReporter, with reports every minute
     *
     * @param prefix graphite metrics prefix
     * @param g a graphite client instance
     * @return a new GraphiteReporter
     */
    public GraphiteReporter getGraphiteReporter(final String prefix,
                                                final Graphite g) {
        final GraphiteReporter reporter =
                GraphiteReporter.forRegistry(registryService.getMetrics()).prefixedWith(prefix)
                        .convertRatesTo(SECONDS).convertDurationsTo(
                        MILLISECONDS).filter(ALL).build(g);
        reporter.start(1, MINUTES);
        LOGGER.debug("Started GraphiteReporter");
        return reporter;
    }

    /**
     * Publish metrics to JMX
     *
     * @param prefix the prefix
     * @return a JMXReporter
     */
    public JmxReporter getJmxReporter(final String prefix) {
        final MBeanServer mbs = getPlatformMBeanServer();
        final JmxReporter reporter =
                JmxReporter.forRegistry(registryService.getMetrics()).registerWith(mbs)
                        .inDomain("org.fcrepo")
                        .convertDurationsTo(MILLISECONDS).convertRatesTo(
                        SECONDS).filter(ALL).build();
        reporter.start();
        LOGGER.debug("Started JmxReporter");
        return reporter;
    }
}
