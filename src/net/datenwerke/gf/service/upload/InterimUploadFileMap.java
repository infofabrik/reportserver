package net.datenwerke.gf.service.upload;

import java.util.concurrent.ConcurrentHashMap;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class InterimUploadFileMap extends ConcurrentHashMap<String, UploadedFile> {

}
