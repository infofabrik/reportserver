/*
// This software is subject to the terms of the Eclipse Public License v1.0
// Agreement, available at the following URL:
// http://www.eclipse.org/legal/epl-v10.html.
// You must accept the terms of that agreement to use this software.
//
// Copyright (C) 2012-2012 Pentaho
// All Rights Reserved.
*/
package mondrian.spi.impl;

import java.io.IOException;
import java.io.InputStream;

import mondrian.spi.VirtualFileHandler;

/**
 * Implementation of {@link VirtualFileHandler} that uses
 * <a href="http://commons.apache.org/vfs/">Apache VFS version 1</a>.
 *
 * @see ApacheVfs2VirtualFileHandler
 *
 * @author jhyde
 */
public class ApacheVfsVirtualFileHandler implements VirtualFileHandler {

   @Override
   public InputStream readVirtualFile(String url) throws IOException {
      // apache vfs version 1 not supported
      throw new UnsupportedOperationException("vfs1 is not supported");
   }

}

// End ApacheVfsVirtualFileHandler.java
