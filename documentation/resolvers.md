#Resolvers

Resolvers are important in StormUi because it helps the module know how to load a file. Standard Storm content will usually use the Uri scheme "cache://path/to/file" which is not a standard scheme. The way Storm resolves this is by using a Resolver class, defined in StormContent and referenced in StormUi.

The default Uri resolvers that get registered are, `file` and `assets` where `file` is a path on the disk, and `assets` is a path in the assets folder of the app.

##Example

```java
new UiSettings.Builder(this)
	.registerUriResolver("cache", new CacheResolver(this))
	.build();
```

This is a basic example of registering a custom Uri scheme for UiSettings. This specific line is required when including StormContent as StormContent has a specific Uri scheme for its content, but this method will allow you to override any Uri scheme you want. See documentation in StormContent for how `cache` Uri scheming works.

