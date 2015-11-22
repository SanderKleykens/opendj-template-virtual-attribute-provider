package glass.storm.opendj.server.extensions.admin.server;

import org.opends.server.admin.server.ConfigurationChangeListener;
import org.opends.server.admin.std.meta.VirtualAttributeCfgDefn;
import org.opends.server.admin.std.server.VirtualAttributeCfg;
import org.opends.server.types.AttributeType;

public interface TemplateVirtualAttributeCfg extends VirtualAttributeCfg {
	Class<? extends TemplateVirtualAttributeCfg> configurationClass();

	void addTemplateChangeListener(ConfigurationChangeListener<TemplateVirtualAttributeCfg> var1);

	void removeTemplateChangeListener(ConfigurationChangeListener<TemplateVirtualAttributeCfg> var1);

	AttributeType getAttributeType();

	VirtualAttributeCfgDefn.ConflictBehavior getConflictBehavior();

	String getJavaClass();

	String getTemplate();
}
