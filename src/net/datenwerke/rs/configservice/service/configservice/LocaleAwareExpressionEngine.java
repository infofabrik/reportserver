package net.datenwerke.rs.configservice.service.configservice;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

import org.apache.commons.configuration.tree.ConfigurationNode;
import org.apache.commons.configuration.tree.ExpressionEngine;
import org.apache.commons.configuration.tree.NodeAddData;

/**
 * Adds support for config files with per-locale properties e.g. &lt;view&gt;
 * &lt;types&gt;net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto&lt;/types&gt;
 * &lt;name&gt;default name}&lt;/name&gt; &lt;name locale="en"&gt;english
 * description&lt;/name&gt; &lt;name locale="de"&gt;deutsche
 * bezeichnung&lt;/name&gt; &lt;/view&gt;
 * 
 */
public class LocaleAwareExpressionEngine implements ExpressionEngine {

	private static final String LOCALE_ATTRIBUTE = "locale";

	private ExpressionEngine originalExpressionEngine;

	private LocalizationServiceImpl localizationService;

	public LocaleAwareExpressionEngine(ExpressionEngine originalExpressionEngine) {
		this.originalExpressionEngine = originalExpressionEngine;
		this.localizationService = new LocalizationServiceImpl();
	}


	@Override
	public List<ConfigurationNode> query(ConfigurationNode root, String key) {
		List<ConfigurationNode> res = originalExpressionEngine.query(root, key);

		/* If there are multiple matching nodes and a node specific to the current locale 
		 * was found, only return this node. Otherwise the default mechanism (return all 
		 * nodes, use first) is applied. 
		 */
		if(res.size() > 1) {
			List<ConfigurationNode> matching = new ArrayList<ConfigurationNode>();
			for(ConfigurationNode node : res){
				if(matchesLocale(node)){
					matching.add(node);
				}
			}
			if(!matching.isEmpty())
				return matching;
		}

		return res;
	}

	private boolean matchesLocale(ConfigurationNode node){
		if(node.getAttributeCount(LOCALE_ATTRIBUTE) == 0)
			return false;
		
		String locale = localizationService.getLocale().getLanguage();
		if(null == locale)
			return false;
		
		for(ConfigurationNode cfgnode : node.getAttributes(LOCALE_ATTRIBUTE)){
			if(locale.equals(cfgnode.getValue()))
				return true;
		}
		return false;
	}

	@Override
	public String nodeKey(ConfigurationNode node, String parentKey) {
		return originalExpressionEngine.nodeKey(node, parentKey);
	}

	@Override
	public NodeAddData prepareAdd(ConfigurationNode root, String key) {
		return originalExpressionEngine.prepareAdd(root, key);
	}

}
