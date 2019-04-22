package com;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AddrDeamonListener implements ServletContextListener, Runnable {

    
    public AddrDeamonListener() {
      
    }

    public void contextDestroyed(ServletContextEvent arg0)  { 
      
    }

    public void contextInitialized(ServletContextEvent arg0)  { 
       
    }

	@Override
	public void run() {
			
	}
	
}
