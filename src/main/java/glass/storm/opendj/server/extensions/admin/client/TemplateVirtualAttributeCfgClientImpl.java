package glass.storm.opendj.server.extensions.admin.client;

import glass.storm.opendj.server.extensions.admin.server.TemplateVirtualAttributeCfg;
import glass.storm.opendj.server.extensions.admin.meta.TemplateVirtualAttributeCfgDefn;
import org.opends.server.admin.IllegalPropertyValueException;
import org.opends.server.admin.ManagedObjectAlreadyExistsException;
import org.opends.server.admin.ManagedObjectDefinition;
import org.opends.server.admin.PropertyProvider;
import org.opends.server.admin.client.*;
import org.opends.server.admin.std.meta.VirtualAttributeCfgDefn;
import org.opends.server.types.AttributeType;
import org.opends.server.types.DN;

import java.util.Collection;
import java.util.SortedSet;

public class TemplateVirtualAttributeCfgClientImpl implements TemplateVirtualAttributeCfgClient {
	private ManagedObject<? extends TemplateVirtualAttributeCfgClient> managedObject;

	public TemplateVirtualAttributeCfgClientImpl(ManagedObject<? extends TemplateVirtualAttributeCfgClient> managedObject) {
		this.managedObject = managedObject;
	}

	@Override
	public ManagedObjectDefinition<? extends TemplateVirtualAttributeCfgClient, ? extends TemplateVirtualAttributeCfg> definition() {
		return TemplateVirtualAttributeCfgDefn.getInstance();
	}

	@Override
	public PropertyProvider properties() {
		return this.managedObject;
	}

	@Override
	public void commit()
			throws ManagedObjectAlreadyExistsException, MissingMandatoryPropertiesException,
			ConcurrentModificationException, OperationRejectedException, AuthorizationException, CommunicationException {
		this.managedObject.commit();
	}

	@Override
	public AttributeType getAttributeType() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getAttributeTypePropertyDefinition());
	}

	@Override
	public void setAttributeType(AttributeType type) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getAttributeTypePropertyDefinition(), type);
	}

	@Override
	public SortedSet<DN> getBaseDN() {
		return this.managedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getBaseDNPropertyDefinition());
	}

	@Override
	public void setBaseDN(Collection<DN> dn) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getBaseDNPropertyDefinition(), dn);
	}

	@Override
	public VirtualAttributeCfgDefn.ConflictBehavior getConflictBehavior() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getConflictBehaviorPropertyDefinition());
	}

	@Override
	public void setConflictBehavior(VirtualAttributeCfgDefn.ConflictBehavior behavior) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getConflictBehaviorPropertyDefinition(), behavior);
	}

	@Override
	public Boolean isEnabled() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getEnabledPropertyDefinition());
	}

	@Override
	public void setEnabled(boolean enabled) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getEnabledPropertyDefinition(), Boolean.valueOf(enabled));
	}

	@Override
	public SortedSet<String> getFilter() {
		return this.managedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getFilterPropertyDefinition());
	}

	@Override
	public void setFilter(Collection<String> filter) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getFilterPropertyDefinition(), filter);
	}

	@Override
	public SortedSet<DN> getGroupDN() {
		return this.managedObject.getPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getGroupDNPropertyDefinition());
	}

	@Override
	public void setGroupDN(Collection<DN> groupDN) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValues(TemplateVirtualAttributeCfgDefn.getInstance().getGroupDNPropertyDefinition(), groupDN);
	}

	@Override
	public String getJavaClass() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getJavaClassPropertyDefinition());
	}

	@Override
	public void setJavaClass(String clazz) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getJavaClassPropertyDefinition(), clazz);
	}

	@Override
	public String getTemplate() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getTemplatePropertyDefinition());
	}

	@Override
	public void setTemplate(String template) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getTemplatePropertyDefinition(), template);
	}

	@Override
	public VirtualAttributeCfgDefn.Scope getScope() {
		return this.managedObject.getPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getScopePropertyDefinition());
	}

	@Override
	public void setScope(VirtualAttributeCfgDefn.Scope scope) throws IllegalPropertyValueException {
		this.managedObject.setPropertyValue(TemplateVirtualAttributeCfgDefn.getInstance().getScopePropertyDefinition(), scope);
	}
}
