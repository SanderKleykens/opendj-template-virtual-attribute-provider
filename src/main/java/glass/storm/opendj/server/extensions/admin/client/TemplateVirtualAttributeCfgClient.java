package glass.storm.opendj.server.extensions.admin.client;

import glass.storm.opendj.server.extensions.admin.server.TemplateVirtualAttributeCfg;
import org.opends.server.admin.IllegalPropertyValueException;
import org.opends.server.admin.ManagedObjectDefinition;
import org.opends.server.admin.std.client.VirtualAttributeCfgClient;
import org.opends.server.admin.std.meta.VirtualAttributeCfgDefn;
import org.opends.server.types.AttributeType;

public interface TemplateVirtualAttributeCfgClient extends VirtualAttributeCfgClient {
	ManagedObjectDefinition<? extends TemplateVirtualAttributeCfgClient, ? extends TemplateVirtualAttributeCfg> definition();

	AttributeType getAttributeType();

	void setAttributeType(AttributeType attributeType) throws IllegalPropertyValueException;

	VirtualAttributeCfgDefn.ConflictBehavior getConflictBehavior();

	void setConflictBehavior(VirtualAttributeCfgDefn.ConflictBehavior conflictBehavior) throws IllegalPropertyValueException;

	String getJavaClass();

	void setJavaClass(String javaClass) throws IllegalPropertyValueException;

	String getTemplate();

	void setTemplate(String template) throws IllegalPropertyValueException;
}
