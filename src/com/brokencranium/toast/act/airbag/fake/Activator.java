package com.brokencranium.toast.act.airbag.fake;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

import com.brokencranium.toast.act.airbag.IAirbag;


public class Activator implements BundleActivator {
	private ServiceRegistration registration;
	private Fakeairbag airbag;

	@Override
	public void start(BundleContext context) throws Exception {
		airbag = new Fakeairbag();
		airbag.startup();
		registration = context.registerService(IAirbag.class.getName(), airbag, null);
          
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		registration.unregister();
		airbag.shutdown();

	}

}
