#Storm Library - Module UI

Storm is a collection of libraries that helps make mobile and desktop applications easy to create using a high quality WYSIACATWYG editor.

This module's purpose is to take a structured collection of JSON files, parse them into POJO objects and render them to the UI using their respective corresponding views.

#Usage

##Gradle

Simply include the following for your gradle dependencies `com.3sidedcube.storm:ui:0.1a`.

**Note** The versioning of the library will always be as follows:

`Major version.Minor version.Bug fix`

It is safe to use `+` in part of of the `Bug fix` version, but do not trust it 100%. Always use a *specific* version to prevent regression errors.

##Code

In your application singleton, add the following code

```java

UiSettings uiSettings = new UiSettings.Builder(this).build();

// Loading app json
String appUri = "assets://app.json";
App app = UiSettings.getInstance().getViewBuilder().buildApp(Uri.parse(appUri));

if (app != null)
{
    UiSettings.getInstance().setApp(app);
}
```

Then in your entry activity add the following code

```java
Intent start = UiSettings.getInstance().getIntentFactory().geIntentForPageUri(this, Uri.parse(UiSettings.getInstance().getApp().getVector()));

if (start != null)
{
	startActivity(start);
}
```

This will use the `vector` string in your App.json to determine what page to load initially.

There are many options in the UiSettings object that allows you to override specific parts of the module.

#Documentation

See the [Javadoc](http://3sidedcube.github.io/Android-LightningUi/) for full in-depth code-level documentation

#Contributors

[Callum Taylor (9A8BAD)](http://keybase.io/scruffyfox), [Matt Allen (DB74F5)](https://keybase.io/mallen), [Alan Le Fournis (067EA0)](https://keybase.io/alan3sc), Luke Reed

#License

See LICENSE.md
