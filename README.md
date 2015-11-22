# OpenDJ Template Virtual Attribute Provider
An OpenDJ extension providing a virtual attribute that can combine other attributes and static strings through a template.

## Installation
1. Build the project or download the packaged JAR
2. Place `src/main/resources/schema/99-template-virtual-attribute.ldif` in `$OPENDJ_INSTALL_DIR/config/schema`
3. Place the JAR (with dependencies) in `$OPENDJ_INSTALL_DIR/lib/extensions`
4. Restart OpenDJ

## Usage
Use `dsconfig` to create or configure template virtual attributes.

For instance, if we want to fill in the "mail" attribute of "Person" entries according to the "<uid>@mydomain.com" template, we would create a virtual attribute using the following command:

```
dsconfig -h localhost -p 4444 \
  -D "cn=Directory Manager" -w password \
  -n create-virtual-attribute \
  --type template --name "mailFromUID" \
  --set attribute-type:mail --set enabled:true \
  --set template:"<uid>@mydomain.com" --set filter:"(objectClass=Person)"
```

For a person with uid "mike.litoris", the virtual attribute would generate "mike.litoris@mydomain.com" as value for the "mail" attribute.

## Contributing
1. Fork
2. Branch (`git checkout -b my-new-feature`)
3. Commit (`git commit -am 'Added some feature'`)
4. Push (`git push origin my-new-feature`)
5. Pull request