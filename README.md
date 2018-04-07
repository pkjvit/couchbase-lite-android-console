# Couchbase Lite Android Console (CbLite Android 1.4)

**Couchbase Lite Android Console** is a sdk tool provide for Couchbase Lite Android 1.4 Applications to debug all database and to track syncing.

## Requirements

- Android 4.1+
- Android Studio 3.0

## Installation

1. Add the following in the dependencies section of the application's build.gradle (the one in the app folder).

```
dependencies {
    compile 'com.pkj.wow.couchbase.lite.android.console:couchbase-lite-android-console:0.1.0-5'
}
```

2. To debug your all database's it needs couchbase Manager and start will redirect to screen which have all data and sync information regarding manger.

```
// Initialise by passing couchbase Manager
CblConsole cblConsole = CblConsole.instance(mManager);

// Start the screen by passing activity context
cblConsole.start(mContext);
```
