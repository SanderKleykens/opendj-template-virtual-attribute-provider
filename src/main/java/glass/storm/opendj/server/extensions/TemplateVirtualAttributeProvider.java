package glass.storm.opendj.server.extensions;

import glass.storm.opendj.server.extensions.admin.server.TemplateVirtualAttributeCfg;
import org.opends.messages.Message;
import org.opends.server.admin.server.ConfigurationChangeListener;
import org.opends.server.api.VirtualAttributeProvider;
import org.opends.server.config.ConfigException;
import org.opends.server.core.DirectoryServer;
import org.opends.server.core.SearchOperation;
import org.opends.server.loggers.debug.DebugTracer;
import org.opends.server.schema.DirectoryStringSyntax;
import org.opends.server.types.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.compiler.STException;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.opends.server.loggers.debug.DebugLogger.getTracer;

public class TemplateVirtualAttributeProvider
		extends VirtualAttributeProvider<TemplateVirtualAttributeCfg>
		implements ConfigurationChangeListener<TemplateVirtualAttributeCfg> {
	public static final String VARIABLE_START_DELIMITER = "<";
	public static final String VARIABLE_END_DELIMITER = ">";

	private static final String TEMPLATE_VARIABLE_REGEX =
			VARIABLE_START_DELIMITER + "([^" + VARIABLE_START_DELIMITER + "]+)" + VARIABLE_END_DELIMITER;
	private static final DebugTracer TRACER = getTracer();

	private TemplateVirtualAttributeCfg currentConfig;
	private Set<String> templateVariables;

	public boolean isConfigurationChangeAcceptable(TemplateVirtualAttributeCfg configuration, List<Message> list) {
		return true;
	}

	public ConfigChangeResult applyConfigurationChange(TemplateVirtualAttributeCfg configuration) {
		setConfiguration(configuration);
		return new ConfigChangeResult(ResultCode.SUCCESS, false);
	}

	@Override
	public void initializeVirtualAttributeProvider(TemplateVirtualAttributeCfg configuration)
			throws ConfigException, InitializationException {
		setConfiguration(configuration);
		configuration.addTemplateChangeListener(this);
	}

	@Override
	public boolean isMultiValued() {
		return false;
	}

	@Override
	public Set<AttributeValue> getValues(Entry entry, VirtualAttributeRule rule) {
		AttributeType destinationAttributeType = rule.getAttributeType();

		try {
			ST stringTemplate = new ST(getConfiguration().getTemplate());

			for (String attribute : templateVariables) {
				AttributeType attributeType = DirectoryServer.getAttributeType(attribute.toLowerCase());

				if (attributeType != null) {
					String attributeValue = entry.getAttributeValue(attributeType, DirectoryStringSyntax.DECODER);

					if (attributeValue != null) {
						stringTemplate.add(attribute, attributeValue);
					} else {
						TRACER.debugMessage(DebugLogLevel.WARNING,
								String.format("Attribute \"%s\" does not have a value for entry \"%s\".",
										attribute, entry.getDN().toNormalizedString()));
						return Collections.emptySet();
					}
				} else {
					TRACER.debugMessage(DebugLogLevel.WARNING, String.format("Attribute \"%s\" does not exist.",
							attribute));
					return Collections.emptySet();
				}
			}

			AttributeValue attributeValue = AttributeValues.create(destinationAttributeType, stringTemplate.render());
			return Collections.singleton(attributeValue);
		} catch(STException ex) {
			TRACER.debugCaught(DebugLogLevel.ERROR, ex);
			return Collections.emptySet();
		} catch (DirectoryException ex) {
			TRACER.debugCaught(DebugLogLevel.ERROR, ex);
			return Collections.emptySet();
		}
	}

	private static Set<String> getVariablesInTemplate(String template) {
		Pattern pattern = Pattern.compile(TEMPLATE_VARIABLE_REGEX);
		Set<String> templateVars = new LinkedHashSet<String>();
		Matcher matcher = pattern.matcher(template);

		while(matcher.find()) {
			templateVars.add(matcher.group(1));
		}

		return templateVars;
	}

	private TemplateVirtualAttributeCfg getConfiguration() {
		return currentConfig;
	}

	private void setConfiguration(TemplateVirtualAttributeCfg configuration) {
		currentConfig = configuration;
		templateVariables = getVariablesInTemplate(configuration.getTemplate());
	}

	@Override
	public boolean isSearchable(VirtualAttributeRule virtualAttributeRule, SearchOperation searchOperation, boolean b) {
		return false;
	}

	@Override
	public void processSearch(VirtualAttributeRule virtualAttributeRule, SearchOperation searchOperation) {
		searchOperation.setResultCode(ResultCode.UNWILLING_TO_PERFORM);
	}
}
