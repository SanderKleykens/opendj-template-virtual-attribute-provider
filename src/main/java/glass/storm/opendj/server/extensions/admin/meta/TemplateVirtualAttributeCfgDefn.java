package glass.storm.opendj.server.extensions.admin.meta;

import glass.storm.opendj.server.extensions.admin.server.TemplateVirtualAttributeCfg;
import glass.storm.opendj.server.extensions.admin.server.TemplateVirtualAttributeCfgServerImpl;
import glass.storm.opendj.server.extensions.TemplateVirtualAttributeProvider;
import glass.storm.opendj.server.extensions.admin.client.TemplateVirtualAttributeCfgClient;
import glass.storm.opendj.server.extensions.admin.client.TemplateVirtualAttributeCfgClientImpl;
import org.opends.server.admin.*;
import org.opends.server.admin.client.*;
import org.opends.server.admin.server.ServerManagedObject;
import org.opends.server.admin.std.meta.VirtualAttributeCfgDefn;
import org.opends.server.api.VirtualAttributeProvider;

public class TemplateVirtualAttributeCfgDefn extends ManagedObjectDefinition<TemplateVirtualAttributeCfgClient, TemplateVirtualAttributeCfg> {
	public static final String VIRTUAL_ATTRIBUTE_NAME = "template-virtual-attribute";

	private static final TemplateVirtualAttributeCfgDefn INSTANCE = new TemplateVirtualAttributeCfgDefn();
	private static final AttributeTypePropertyDefinition PD_ATTRIBUTE_TYPE = buildAttributeTypeProperty();
	private static final EnumPropertyDefinition<VirtualAttributeCfgDefn.ConflictBehavior> PD_CONFLICT_BEHAVIOR = buildConflictBehaviorProperty();
	private static final ClassPropertyDefinition PD_JAVA_CLASS = buildJavaClassProperty();
	private static final StringPropertyDefinition PD_TEMPLATE = buildTemplateProperty();

	private static final String ATTRIBUTE_TYPE_NAME = "attribute-type";
	private static final String CONFLICT_BEHAVIOR_NAME = "conflict-behavior";
	private static final String JAVA_CLASS_NAME = "java-class";
	private static final String TEMPLATE_NAME = "template";
	private static final String DEFAULT_CONFLICT_BEHAVIOR = "real-overrides-virtual";

	private TemplateVirtualAttributeCfgDefn() {
		super(VIRTUAL_ATTRIBUTE_NAME, VirtualAttributeCfgDefn.getInstance());
	}

	public static TemplateVirtualAttributeCfgDefn getInstance() {
		return INSTANCE;
	}

	@Override
	public TemplateVirtualAttributeCfgClient createClientConfiguration(ManagedObject<? extends TemplateVirtualAttributeCfgClient> managedObject) {
		return new TemplateVirtualAttributeCfgClientImpl(managedObject);
	}

	@Override
	public TemplateVirtualAttributeCfg createServerConfiguration(ServerManagedObject<? extends TemplateVirtualAttributeCfg> serverManagedObject) {
		return new TemplateVirtualAttributeCfgServerImpl(serverManagedObject);
	}

	@Override
	public Class<TemplateVirtualAttributeCfg> getServerConfigurationClass() {
		return TemplateVirtualAttributeCfg.class;
	}

	public AttributeTypePropertyDefinition getAttributeTypePropertyDefinition() {
		return PD_ATTRIBUTE_TYPE;
	}

	public DNPropertyDefinition getBaseDNPropertyDefinition() {
		return VirtualAttributeCfgDefn.getInstance().getBaseDNPropertyDefinition();
	}

	public EnumPropertyDefinition<VirtualAttributeCfgDefn.ConflictBehavior> getConflictBehaviorPropertyDefinition() {
		return PD_CONFLICT_BEHAVIOR;
	}

	public BooleanPropertyDefinition getEnabledPropertyDefinition() {
		return VirtualAttributeCfgDefn.getInstance().getEnabledPropertyDefinition();
	}

	public StringPropertyDefinition getFilterPropertyDefinition() {
		return VirtualAttributeCfgDefn.getInstance().getFilterPropertyDefinition();
	}

	public DNPropertyDefinition getGroupDNPropertyDefinition() {
		return VirtualAttributeCfgDefn.getInstance().getGroupDNPropertyDefinition();
	}

	public ClassPropertyDefinition getJavaClassPropertyDefinition() {
		return PD_JAVA_CLASS;
	}

	public EnumPropertyDefinition<VirtualAttributeCfgDefn.Scope> getScopePropertyDefinition() {
		return VirtualAttributeCfgDefn.getInstance().getScopePropertyDefinition();
	}

	public StringPropertyDefinition getTemplatePropertyDefinition() {
		return PD_TEMPLATE;
	}

	private static AttributeTypePropertyDefinition buildAttributeTypeProperty() {
		AttributeTypePropertyDefinition.Builder attributeTypePropertyBuilder =
				AttributeTypePropertyDefinition.createBuilder(INSTANCE, ATTRIBUTE_TYPE_NAME);

		attributeTypePropertyBuilder.setOption(PropertyOption.MANDATORY);
		attributeTypePropertyBuilder.setAdministratorAction(new AdministratorAction(AdministratorAction.Type.NONE,
				INSTANCE, ATTRIBUTE_TYPE_NAME));
		attributeTypePropertyBuilder.setDefaultBehaviorProvider(new UndefinedDefaultBehaviorProvider());

		AttributeTypePropertyDefinition definition = attributeTypePropertyBuilder.getInstance();
		INSTANCE.registerPropertyDefinition(definition);

		return definition;
	}

	private static EnumPropertyDefinition<VirtualAttributeCfgDefn.ConflictBehavior> buildConflictBehaviorProperty() {
		DefinedDefaultBehaviorProvider provider = new DefinedDefaultBehaviorProvider(new String[]{ DEFAULT_CONFLICT_BEHAVIOR });
		EnumPropertyDefinition.Builder conflictBehaviorPropertyBuilder =
				EnumPropertyDefinition.createBuilder(INSTANCE, CONFLICT_BEHAVIOR_NAME);

		conflictBehaviorPropertyBuilder.setOption(PropertyOption.ADVANCED);
		conflictBehaviorPropertyBuilder.setAdministratorAction(new AdministratorAction(AdministratorAction.Type.NONE,
				INSTANCE, CONFLICT_BEHAVIOR_NAME));
		conflictBehaviorPropertyBuilder.setDefaultBehaviorProvider(provider);
		conflictBehaviorPropertyBuilder.setEnumClass(VirtualAttributeCfgDefn.ConflictBehavior.class);

		EnumPropertyDefinition<VirtualAttributeCfgDefn.ConflictBehavior> definition =
				(EnumPropertyDefinition<VirtualAttributeCfgDefn.ConflictBehavior>) conflictBehaviorPropertyBuilder.getInstance();
		INSTANCE.registerPropertyDefinition(definition);

		return definition;
	}

	private static ClassPropertyDefinition buildJavaClassProperty() {
		DefinedDefaultBehaviorProvider provider = new DefinedDefaultBehaviorProvider(new String[]{TemplateVirtualAttributeProvider.class.getName()});
		ClassPropertyDefinition.Builder javaClassPropertyBuilder =
				ClassPropertyDefinition.createBuilder(INSTANCE, JAVA_CLASS_NAME);

		javaClassPropertyBuilder.setOption(PropertyOption.MANDATORY);
		javaClassPropertyBuilder.setOption(PropertyOption.ADVANCED);
		javaClassPropertyBuilder.setAdministratorAction(new AdministratorAction(AdministratorAction.Type.COMPONENT_RESTART,
				INSTANCE, JAVA_CLASS_NAME));
		javaClassPropertyBuilder.setDefaultBehaviorProvider(provider);
		javaClassPropertyBuilder.addInstanceOf(VirtualAttributeProvider.class.getName());

		ClassPropertyDefinition definition = javaClassPropertyBuilder.getInstance();
		INSTANCE.registerPropertyDefinition(definition);

		return definition;
	}

	private static StringPropertyDefinition buildTemplateProperty() {
		StringPropertyDefinition.Builder templatePropertyBuilder = StringPropertyDefinition.createBuilder(INSTANCE,
				TEMPLATE_NAME);

		templatePropertyBuilder.setOption(PropertyOption.MANDATORY);
		templatePropertyBuilder.setAdministratorAction(new AdministratorAction(AdministratorAction.Type.NONE,
				INSTANCE, TEMPLATE_NAME));
		templatePropertyBuilder.setDefaultBehaviorProvider(new UndefinedDefaultBehaviorProvider());

		StringPropertyDefinition definition = templatePropertyBuilder.getInstance();
		INSTANCE.registerPropertyDefinition(definition);

		return definition;
	}

}
