package edu.dfci.cccb.mev.configuration.persistense.h2;

import static java.lang.String.valueOf;
import static org.h2.tools.Server.createTcpServer;

import java.sql.SQLException;

import lombok.Synchronized;
import lombok.extern.log4j.Log4j;

import org.h2.tools.Server;
import org.springframework.context.Lifecycle;

@Log4j
public class H2Server implements Lifecycle {

  private Server server;  
  private final int port;
  private String name;  
  private final String[] args;
  
  public H2Server (String name, int port, String ... args) {
    this.name = name;
    this.args = new String[args.length+2];
    this.args[0] = "-tcpPort";
    this.args[1] = valueOf(port);
    for(int i=2;i<this.args.length;i++)
      this.args[i]=args[i-2];      
    this.port = port;    
  }

  @Override
  @Synchronized  
  public void start () {
    try {
      server = (createTcpServer (args)).start();      
      log.info ("Started H2 TcpServer '"+name+"' with args: " + args);
    } catch (SQLException e) {
      log.warn ("Failed to start H2 TcpServer '"+name+"' with args " + args, e);
    }

  }

  /* (non-Javadoc)
   * @see org.springframework.context.Lifecycle#stop() */
  @Override
  @Synchronized
  public void stop () {
    server.stop ();
    log.info ("Stopped H2 TcpServer '"+name+"'");
    server = null;
  }

  /* (non-Javadoc)
   * @see org.springframework.context.Lifecycle#isRunning() */
  @Override
  @Synchronized
  public boolean isRunning () {
    return server != null;
  }
  
}
