package glass.storm.opendj.server.extensions.admin.server;

import glass.storm.opendj.server.extensions.admin.meta.TemplateVirtualAttributeCfgDefn;
import org.opends.server.admin.server.ConfigurationChangeListener;
import org.opends.server.admin.server.ServerManagedObject;
import org.opends.server.admin.std.meta.VirtualAttributeCfgDefn;
import org.opends.server.admin.std.server.VirtualAttributeCfg;
import org.opends.server.types.AttributeType;
import org.opends.server.types.DN;

import java.util.SortedSet;

public class TemplateVirtualAttributeCfgServerImpl implements TemplateVirtualAttributeCfg {
	private ServerManagedObject<? extends TemplateVirtualAttributeCfg> serverManagedObject;
	private final AttributeType pAttributeType;
	private final SortedSet<DN> pBaseDN;
	private final VirtualAttributeCfgDefn.ConflictBehavior pConflictBehavior;
	private final boolean pEnabled;
	private final SortedSet<String> pFilter;
	private final SortedSet<DN> pGroupDN;
	private final String pJavaClass;
	private final VirtualAttributeCfgDefn.Scope pScope;
	private final String pTemplate;

	public TemplateVirtualAttributeCfgServerImpl(ServerManagedObject<? extends TemplateVirtualAttributeCfg> serverManagedObject) {
		this.serverManagedObject = serverManagedObject;
		this.pAttributeType = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getAttributeTypePropertyDefinition());
		this.pBaseDN = serverManagedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getBaseDNPropertyDefinition());
		this.pConflictBehavior = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getConflictBehaviorPropertyDefinition());
		this.pEnabled = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getEnabledPropertyDefinition()).booleanValue();
		this.pFilter = serverManagedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getFilterPropertyDefinition());
		this.pGroupDN = serverManagedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getGroupDNPropertyDefinition());
		this.pJavaClass = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getJavaClassPropertyDefinition());
		this.pScope = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getScopePropertyDefinition());
		this.pTemplate = serverManagedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getTemplatePropertyDefinition());
	}

	@Override
	public DN dn() {
		return this.serverManagedObject.getDN();
	}

	@Override
	public Class<? extends TemplateVirtualAttributeCfg> configurationClass() {
		return TemplateVirtualAttributeCfg.class;
	}

	@Override
	public void addChangeListener(ConfigurationChangeListener<VirtualAttributeCfg> listener) {
		this.serverManagedObject.registerChangeListener(listener);
	}

	@Override
	public void removeChangeListener(ConfigurationChangeListener<VirtualAttributeCfg> listener) {
		this.serverManagedObject.deregisterChangeListener(listener);
	}

	@Override
	public void addTemplateChangeListener(ConfigurationChangeListener<TemplateVirtualAttributeCfg> listener) {
		this.serverManagedObject.registerChangeListener(listener);
	}

	@Override
	public void removeTemplateChangeListener(ConfigurationChangeListener<TemplateVirtualAttributeCfg> listener) {
		this.serverManagedObject.deregisterChangeListener(listener);
	}

	@Override
	public AttributeType getAttributeType() {
		return this.pAttributeType;
	}

	@Override
	public SortedSet<DN> getBaseDN() {
		return this.pBaseDN;
	}

	@Override
	public VirtualAttributeCfgDefn.ConflictBehavior getConflictBehavior() {
		return this.pConflictBehavior;
	}

	@Override
	public boolean isEnabled() {
		return this.pEnabled;
	}

	@Override
	public SortedSet<String> getFilter() {
		return this.pFilter;
	}

	@Override
	public SortedSet<DN> getGroupDN() {
		return this.pGroupDN;
	}

	@Override
	public String getJavaClass() {
		return this.pJavaClass;
	}

	@Override
	public String getTemplate() {
		return this.pTemplate;
	}

	@Override
	public VirtualAttributeCfgDefn.Scope getScope() {
		return this.pScope;
	}
}
