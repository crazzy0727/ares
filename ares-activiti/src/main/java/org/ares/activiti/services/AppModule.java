package org.ares.activiti.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.services.LibraryMapping;
import org.ares.activiti.general.services.GeneralModule;

@SubModule(GeneralModule.class)
public class AppModule
{
    public static void bind(ServiceBinder binder)
    {
        
    }


    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("activiti", "com.ares.activiti"));
    }
    
    public static void contributeClasspathAssetAliasManager(MappedConfiguration<String, String> configuration)
    {
        configuration.add("ares-activiti", "activiti");
    }
    
    public static void contributeApplicationDefaults(
            MappedConfiguration<String, Object> configuration)
    {
        configuration.add(SymbolConstants.PRODUCTION_MODE, false);
        configuration.add(SymbolConstants.APPLICATION_VERSION, "0.0.1-QA");
    }
}
