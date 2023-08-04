package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
*/
@NonNullApi
public class LibrariesForLibs extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final JacksonLibraryAccessors laccForJacksonLibraryAccessors = new JacksonLibraryAccessors(owner);
    private final JetbrainsLibraryAccessors laccForJetbrainsLibraryAccessors = new JetbrainsLibraryAccessors(owner);
    private final PluginLibraryAccessors laccForPluginLibraryAccessors = new PluginLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibs(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects) {
        super(config, providers, objects);
    }

        /**
         * Creates a dependency provider for dnsOverHttps (com.squareup.okhttp3:okhttp-dnsoverhttps)
         * This dependency was declared in settings file 'settings.gradle.kts'
         */
        public Provider<MinimalExternalModuleDependency> getDnsOverHttps() { return create("dnsOverHttps"); }

    /**
     * Returns the group of libraries at jackson
     */
    public JacksonLibraryAccessors getJackson() { return laccForJacksonLibraryAccessors; }

    /**
     * Returns the group of libraries at jetbrains
     */
    public JetbrainsLibraryAccessors getJetbrains() { return laccForJetbrainsLibraryAccessors; }

    /**
     * Returns the group of libraries at plugin
     */
    public PluginLibraryAccessors getPlugin() { return laccForPluginLibraryAccessors; }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() { return vaccForVersionAccessors; }

    /**
     * Returns the group of bundles at bundles
     */
    public BundleAccessors getBundles() { return baccForBundleAccessors; }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() { return paccForPluginAccessors; }

    public static class JacksonLibraryAccessors extends SubDependencyFactory {
        private final JacksonDataformatLibraryAccessors laccForJacksonDataformatLibraryAccessors = new JacksonDataformatLibraryAccessors(owner);

        public JacksonLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotations (com.fasterxml.jackson.core:jackson-annotations)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() { return create("jackson.annotations"); }

            /**
             * Creates a dependency provider for core (com.fasterxml.jackson.core:jackson-core)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getCore() { return create("jackson.core"); }

            /**
             * Creates a dependency provider for databind (com.fasterxml.jackson.core:jackson-databind)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getDatabind() { return create("jackson.databind"); }

            /**
             * Creates a dependency provider for datatypeJsr310 (com.fasterxml.jackson.datatype:jackson-datatype-jsr310)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getDatatypeJsr310() { return create("jackson.datatypeJsr310"); }

        /**
         * Returns the group of libraries at jackson.dataformat
         */
        public JacksonDataformatLibraryAccessors getDataformat() { return laccForJacksonDataformatLibraryAccessors; }

    }

    public static class JacksonDataformatLibraryAccessors extends SubDependencyFactory {

        public JacksonDataformatLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for xml (com.fasterxml.jackson.dataformat:jackson-dataformat-xml)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getXml() { return create("jackson.dataformat.xml"); }

    }

    public static class JetbrainsLibraryAccessors extends SubDependencyFactory {

        public JetbrainsLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for annotations (org.jetbrains:annotations)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getAnnotations() { return create("jetbrains.annotations"); }

    }

    public static class PluginLibraryAccessors extends SubDependencyFactory {
        private final PluginDependencyLibraryAccessors laccForPluginDependencyLibraryAccessors = new PluginDependencyLibraryAccessors(owner);
        private final PluginNexusLibraryAccessors laccForPluginNexusLibraryAccessors = new PluginNexusLibraryAccessors(owner);
        private final PluginOpenapiLibraryAccessors laccForPluginOpenapiLibraryAccessors = new PluginOpenapiLibraryAccessors(owner);
        private final PluginSwaggerLibraryAccessors laccForPluginSwaggerLibraryAccessors = new PluginSwaggerLibraryAccessors(owner);

        public PluginLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for checksum (gradle.plugin.org.gradle.crypto:checksum)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getChecksum() { return create("plugin.checksum"); }

        /**
         * Returns the group of libraries at plugin.dependency
         */
        public PluginDependencyLibraryAccessors getDependency() { return laccForPluginDependencyLibraryAccessors; }

        /**
         * Returns the group of libraries at plugin.nexus
         */
        public PluginNexusLibraryAccessors getNexus() { return laccForPluginNexusLibraryAccessors; }

        /**
         * Returns the group of libraries at plugin.openapi
         */
        public PluginOpenapiLibraryAccessors getOpenapi() { return laccForPluginOpenapiLibraryAccessors; }

        /**
         * Returns the group of libraries at plugin.swagger
         */
        public PluginSwaggerLibraryAccessors getSwagger() { return laccForPluginSwaggerLibraryAccessors; }

    }

    public static class PluginDependencyLibraryAccessors extends SubDependencyFactory {

        public PluginDependencyLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for analysis (com.autonomousapps:dependency-analysis-gradle-plugin)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getAnalysis() { return create("plugin.dependency.analysis"); }

    }

    public static class PluginNexusLibraryAccessors extends SubDependencyFactory {

        public PluginNexusLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for publish (io.github.gradle-nexus:publish-plugin)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getPublish() { return create("plugin.nexus.publish"); }

    }

    public static class PluginOpenapiLibraryAccessors extends SubDependencyFactory {
        private final PluginOpenapiMergerLibraryAccessors laccForPluginOpenapiMergerLibraryAccessors = new PluginOpenapiMergerLibraryAccessors(owner);

        public PluginOpenapiLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

        /**
         * Returns the group of libraries at plugin.openapi.merger
         */
        public PluginOpenapiMergerLibraryAccessors getMerger() { return laccForPluginOpenapiMergerLibraryAccessors; }

    }

    public static class PluginOpenapiMergerLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public PluginOpenapiMergerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for merger (com.rameshkp:openapi-merger-gradle-plugin)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("plugin.openapi.merger"); }

            /**
             * Creates a dependency provider for app (com.rameshkp:openapi-merger-app)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getApp() { return create("plugin.openapi.merger.app"); }

    }

    public static class PluginSwaggerLibraryAccessors extends SubDependencyFactory implements DependencyNotationSupplier {

        public PluginSwaggerLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for swagger (io.swagger.core.v3:swagger-gradle-plugin)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> asProvider() { return create("plugin.swagger"); }

            /**
             * Creates a dependency provider for generator (gradle.plugin.org.hidetake:gradle-swagger-generator-plugin)
             * This dependency was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<MinimalExternalModuleDependency> getGenerator() { return create("plugin.swagger.generator"); }

    }

    public static class VersionAccessors extends VersionFactory  {

        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: jackson (2.15.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<String> getJackson() { return getVersion("jackson"); }

            /**
             * Returns the version associated to this alias: jetbrainsAnnotation (24.0.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<String> getJetbrainsAnnotation() { return getVersion("jetbrainsAnnotation"); }

            /**
             * Returns the version associated to this alias: okhttp (4.9.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in settings file 'settings.gradle.kts'
             */
            public Provider<String> getOkhttp() { return getVersion("okhttp"); }

    }

    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config) { super(objects, providers, config); }

    }

    public static class PluginAccessors extends PluginFactory {

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for publish to the plugin id 'com.gradle.plugin-publish'
             * This plugin was declared in catalog org.eclipse.edc:edc-versions:0.0.1-SNAPSHOT:20230525.065120-177
             */
            public Provider<PluginDependency> getPublish() { return createPlugin("publish"); }

    }

}
