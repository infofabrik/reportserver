package net.datenwerke.gf.client.upload.html5;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.sencha.gxt.widget.core.client.Component;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.locale.BaseMessages;

public class Html5FileUploadListener {

   public interface UploadCallback {
      void fileUploaded(String name, long size, String base64);

      void allFilesUploaded(List<FileToUpload> list);

      void fireOnDropStart(int nrOfFiles);
   }

   private final UploadCallback callback;

   private String maskText = BaseMessages.INSTANCE.validUploadTarget();
   private boolean addHoverClass = false;
   private String hoverClassName = "";

   private List<FileToUpload> files = new ArrayList<FileToUpload>();

   private HandlerRegistration attachHandlerRegistrar;

   private Component component;

   public Html5FileUploadListener(UploadCallback callback, final Component component) {
      this.callback = callback;
      this.component = component;

      /* style */
      hoverClassName = "rs-html5-upload-hover";

      if (component.isAttached())
         attachFileUploadListener(this, component.getId());
      else {
         attachHandlerRegistrar = component.addAttachHandler(new Handler() {

            @Override
            public void onAttachOrDetach(AttachEvent event) {
               attachFileUploadListener(Html5FileUploadListener.this, component.getId());
               attachHandlerRegistrar.removeHandler();
            }
         });
      }
   }

   protected void onDragStart() {
      if (addHoverClass)
         component.getElement().addClassName(hoverClassName);
      else
         component.mask(getMaskText());
   }

   protected void onDragEnd() {
      if (addHoverClass)
         component.getElement().removeClassName(hoverClassName);
      else
         component.unmask();
   }

   protected void log(String e) {
      System.out.println(e);
   }

   public String getMaskText() {
      return maskText;
   }

   public void setMaskText(String maskText) {
      this.maskText = maskText;
   }

   public String getHoverClassName() {
      return hoverClassName;
   }

   public void setHoverClassName(String hoverClassName) {
      this.hoverClassName = hoverClassName;
      setAddHoverClass(true);
   }

   public void setAddHoverClass(boolean addHoverClass) {
      this.addHoverClass = addHoverClass;
   }

   public boolean isAddHoverClass() {
      return addHoverClass;
   }

   protected static native void attachFileUploadListener(Html5FileUploadListener instance, String id) /*-{
	    var filedrag = $doc.getElementById(id);
	  	
	  	if(null == filedrag)
	  		return;
	  
//	    var collection = $wnd.$();
	    
	    function FileDragLeave(e) {
 			e.stopPropagation();  
		    e.preventDefault();
		    	    		
//	    	setTimeout(function() {
//                collection = collection.not(e.target);
//                instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::log(Ljava/lang/String;)(e.type + collection.size() + e.target.id);
//                if (collection.size() === 0) {
            instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::onDragEnd()();
//                }
//            }, 10);
	    }
	     
	    function FileDragEnter(e) {
 			e.stopPropagation();  
		    e.preventDefault();

//	    	instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::log(Ljava/lang/String;)(e.type + collection.size() + e.target.id);
//		    if (collection.size() === 0) {
                instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::onDragStart()();
//            }
//            collection = collection.add(e.target);
		}
	    
		function FileSelectHandler(e) {
//			instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::log(Ljava/lang/String;)('drop');
			
			// detach handler during upload
		    filedrag.removeEventListener("drop", FileSelectHandler, false);
		    
		    // cancel event and hover styling  
		    e.stopPropagation();  
		    e.preventDefault();
		    instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::onDragEnd()();
		    
		    // fetch FileList object  
		    var files = e.target.files || e.dataTransfer.files;
		    
		    var size = files.length;
		    var processed = 0;
		    
//		    instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::log(Ljava/lang/String;)('process');

			instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::fireOnDropStart(I)(size);
		    
		    // process all File objects  
		    for (var i = 0, f; f = files[i]; i++) {  
		        var reader = new FileReader();  
			    reader.onload = function(file){ 
			    	return function(e){
				    	processed++;
				    	
				    	instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::fireOnDrop(Ljava/lang/String;ILjava/lang/String;)(file.name, file.size, e.target.result);
				    	
				    	if(size == processed){
				    		filedrag.addEventListener("drop", FileSelectHandler, false);
				    		instance.@net.datenwerke.gf.client.upload.html5.Html5FileUploadListener::uploadDone()();
				    	}
			    }}(f);
			    reader.readAsDataURL(f); 
		    }  
	   }
		
	   // is XHR2 available?  
	   var xhr = new XMLHttpRequest();  
	   if (xhr.upload) {  
	   	
	   	   new $wnd.Dragster( filedrag );
	   	
	       // file drop  
	       filedrag.addEventListener("dragster:enter", FileDragEnter, false);  
	       filedrag.addEventListener("dragster:leave", FileDragLeave, false);
	       filedrag.addEventListener("dragenter", function(e){
	       	 e.stopPropagation();  
		     e.preventDefault();
		     return false;
	       }, false);
	       filedrag.addEventListener("dragover", function(e){
	       	 e.stopPropagation();  
		     e.preventDefault();
		     return false;
	       }, false);  
	       filedrag.addEventListener("drop", FileSelectHandler, false);  
	       filedrag.style.display = "block";  
	   }   
	}-*/;

   protected void fireOnDropStart(int nrOfFiles) {
      callback.fireOnDropStart(nrOfFiles);
   }

   protected void fireOnDrop(String name, int size, String base64) {
      files.add(new FileToUpload(name, size, base64));
      callback.fileUploaded(name, size, base64);
   }

   protected void uploadDone() {
      callback.allFilesUploaded(new ArrayList<FileToUpload>(files));
      files.clear();
   }
}
