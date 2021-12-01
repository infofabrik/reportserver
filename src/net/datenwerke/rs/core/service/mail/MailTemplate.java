package net.datenwerke.rs.core.service.mail;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.rs.utils.juel.SimpleJuel;

public class MailTemplate {
	
	private String subjectTemplate;
	private String messageTemplate;
	private Map<String, Object> dataMap;
	
	private boolean html = false;
	
	public MailTemplate() {
		this.dataMap = new HashMap<String, Object>();
	}
	
	public MailTemplate(String subjectTemplate, String messageTemplate, Map<String, Object> dataMap) {
		this.subjectTemplate = subjectTemplate;
		this.messageTemplate = messageTemplate;
		this.dataMap = dataMap;
	}

	public void addReplacement(String key, Object value){
		dataMap.put(key, value);
	}
	
	public String getSubjectTemplate() {
		return subjectTemplate;
	}

	public void setSubjectTemplate(String subjectTemplate) {
		this.subjectTemplate = subjectTemplate;
	}

	public String getMessageTemplate() {
		return messageTemplate;
	}

	public void setMessageTemplate(String messageTemplate) {
		this.messageTemplate = messageTemplate;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	public void setHtml(boolean html) {
		this.html = html;
	}

	public boolean isHtml() {
		return html;
	}
	

	public void configureMail(SimpleMail mail, SimpleJuel juel,
			SimpleAttachment ... attachments) {
		
		getDataMap().entrySet().stream()
			.forEach(e -> juel.addReplacement(e.getKey(), e.getValue()));
		
		if(isHtml())
			mail.setHtml(juel.parse(getMessageTemplate()), attachments);
		else
			mail.setContent(juel.parse(getMessageTemplate()), attachments);

		mail.setSubject(juel.parse(getSubjectTemplate()));

	}
	
}
