package net.datenwerke.rs.legacysaiku.server.rest.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import net.datenwerke.gf.service.localization.RemoteMessageService;
import net.datenwerke.rs.legacysaiku.client.saiku.locale.SaikuNativeMessages;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.common.collect.ImmutableList;

@Path("/legacysaiku/i18n")
@XmlAccessorType(XmlAccessType.NONE)
public class SaikuI18nResource {

	private Provider<ServletContext> servletContext;
	private RemoteMessageService remoteMessageService;

	@Inject
	public SaikuI18nResource(
			Provider<ServletContext> servletContext, 
			RemoteMessageService remoteMessageService) {
		this.servletContext = servletContext;
		this.remoteMessageService = remoteMessageService;
	}

	@GET
	@Path("/{lang}")
	@Produces({"application/json" })
	public Map<String, String> getMapping(@PathParam("lang") String lang) throws JsonParseException, IOException {
		Map<String, String> mapping = new HashMap<String, String>();

		/* try loading original json */
		String esclang = lang.replaceAll("\\W", "");
		InputStream originalMapping = servletContext.get().getResourceAsStream("/resources/legacysaiku/js/saiku/plugins/I18n/po/" + esclang + ".json");
		if(null != originalMapping){
			ObjectMapper mapper = new ObjectMapper();
			JsonNode jsonMapping = mapper.readTree(originalMapping);

			for(Entry<String, JsonNode> e : ImmutableList.copyOf(jsonMapping.getFields())){
				mapping.put(e.getKey(), e.getValue().asText());
			}

		}
		
		/* overlay rs messages */
		HashMap<String,HashMap<String,String>> messages = remoteMessageService.getMessages(lang);
		if(null != messages){
			HashMap<String,String> rsmap = messages.get(SaikuNativeMessages.class.getName());
			mapping.putAll(rsmap);
		}
		
		return mapping;
	}
}
