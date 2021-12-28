package net.datenwerke.rs.utils.mockrequest;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import org.apache.sshd.common.session.SessionContext;

public class MockHttpSessionWrapper implements HttpSession {

   public static final Map<String, Map<String, Object>> valueMap = new HashMap<>();

   private SessionContext session;

   public void setSession(SessionContext session) {
      this.session = session;
   }

   @Override
   public Object getAttribute(String key) {
      if (valueMap.containsKey(new String(session.getSessionId())))
         return valueMap.get(new String(session.getSessionId())).get(key);
      return null;
   }

   @Override
   public Enumeration getAttributeNames() {
      return null;
   }

   @Override
   public long getCreationTime() {
      return 0;
   }

   @Override
   public String getId() {
      return new String(session.getSessionId());
   }

   @Override
   public long getLastAccessedTime() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public int getMaxInactiveInterval() {
      // TODO Auto-generated method stub
      return 0;
   }

   @Override
   public ServletContext getServletContext() {
      return null;
   }

   @Override
   public HttpSessionContext getSessionContext() {
      return null;
   }

   @Override
   public Object getValue(String key) {
      if (valueMap.containsKey(new String(session.getSessionId())))
         return valueMap.get(new String(session.getSessionId())).get(key);
      return null;
   }

   @Override
   public String[] getValueNames() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void invalidate() {
      if (valueMap.containsKey(new String(session.getSessionId())))
         valueMap.get(new String(session.getSessionId())).clear();
   }

   @Override
   public boolean isNew() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void putValue(String key, Object value) {
      if (!valueMap.containsKey(new String(session.getSessionId())))
         valueMap.put(new String(session.getSessionId()), new HashMap<String, Object>());
      valueMap.get(new String(session.getSessionId())).put(key, value);
   }

   @Override
   public void removeAttribute(String key) {
      if (valueMap.containsKey(new String(session.getSessionId())))
         valueMap.get(new String(session.getSessionId())).remove(key);
   }

   @Override
   public void removeValue(String arg0) {

   }

   @Override
   public void setAttribute(String key, Object value) {
      if (!valueMap.containsKey(new String(session.getSessionId())))
         valueMap.put(new String(session.getSessionId()), new HashMap<String, Object>());

      valueMap.get(new String(session.getSessionId())).put(key, value);
   }

   @Override
   public void setMaxInactiveInterval(int arg0) {
      // TODO Auto-generated method stub

   }

}
