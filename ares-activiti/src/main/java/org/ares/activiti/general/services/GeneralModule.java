package org.ares.activiti.general.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.services.LibraryMapping;


public class GeneralModule {
	
    public static void contributeComponentClassResolver(Configuration<LibraryMapping> configuration)
    {
        configuration.add(new LibraryMapping("general", "org.ares.activiti.general"));
    }
}
