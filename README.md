# Couchbase Lite Android Console (CbLite Android 1.4)

**Couchbase Lite Android Console** is a sdk tool provide for Couchbase Lite Android 1.4 Applications to debug all database and to track syncing.

## Screen Shots

<div>
<img src="https://github.com/pkjvit/couchbase-lite-android-console/blob/master/ScreenShots/cblite_console_01.png" width="400">
<img width="20">
<img src="https://github.com/pkjvit/couchbase-lite-android-console/blob/master/ScreenShots/cblite_console_02.png" width="400">
</div>

## Requirements

- Android 4.1+
- Android Studio 3.0

## Installation

1. Add the following in the dependencies section of the application's build.gradle (the one in the app folder).

```
dependencies {
    compile 'com.pkj.wow.couchbase.lite.android.console:couchbase-lite-android-console:0.1.0-7'
}
```

2. To debug your all database's it needs couchbase Manager and start will redirect to screen which have all data and sync information regarding manger.

```
// Initialise by passing couchbase Manager
CblConsole cblConsole = CblConsole.instance(mManager);

// Start the screen by passing activity context
cblConsole.start(mContext);
```


## Licence
    Copyright 2018 Pankaj Jangid

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
