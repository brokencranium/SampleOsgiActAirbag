package com.brokencranium.toast.act.airbag.fake;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.brokencranium.toast.act.airbag.IAirBagListener;

public class Fakeairbag implements com.brokencranium.toast.act.airbag.IAirbag {
	private List listeners = new ArrayList();
    private Job job;
    private boolean isRunning;
    
    public Fakeairbag() {
        listeners = new ArrayList();
    }

    public Fakeairbag(List listeners) {
        this.listeners = listeners;
    }


	public synchronized void addListener(IAirBagListener listener) {
        listeners.add(listener);
    }

    @Override
	public synchronized void deploy(){
        for(Iterator i = listeners.iterator();i.hasNext();){
            ((IAirBagListener) i.next()).deployed();
        }
    }


	public synchronized void removeListener(IAirBagListener listener){
        listeners.remove(listener);
    }
    
    public synchronized void shutdown(){
    	isRunning = false;
    	job.cancel();
    	try{
    		job.join();
    	}catch(InterruptedException e){
    	  System.out.println("Shutting down ...");
    	}
    }
    
    public synchronized void startup(){
    	isRunning = true;
    	job = new Job("FakeAirbag"){
    		protected IStatus run(IProgressMonitor monitor){
    			deploy();
    			if (isRunning)
    				schedule(5000);
    			return Status.OK_STATUS;
    		}
    	};
    	job.schedule(5000);
    	
    }

}
