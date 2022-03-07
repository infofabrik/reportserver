package net.datenwerke.rs.terminal.service.terminal

import javax.net.ssl.SSLSocketFactory

class SslHelperServiceImpl implements SslHelperService {
   
   /*
    * Tests SSL. Based on SSLPoke.java:
    * https://confluence.atlassian.com/download/attachments/117455/SSLPoke.java */
   @Override
   public boolean sslTest(String host, int port) {
      def sslsocketfactory = SSLSocketFactory.default
      def sslsocket = sslsocketfactory.createSocket host, port
      
      def is = sslsocket.inputStream
      def out = sslsocket.outputStream
      
      // Write a test byte to get a reaction :)
      out.write 1
      
      while (is.available() > 0) 
         is.read()
        
      true
   }
   
}