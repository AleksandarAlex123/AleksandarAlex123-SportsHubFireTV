Build & Run the App (Emulator vs Physical Device)
Running on an Emulator

The app can run on an Android TV emulator or real device
Scrolling and focus updates may feel slower
Fire TV–specific behavior chere is similar like other TV APS, because this is small app without some Amazon services and limitations.

Recommended emulator:
Android TV (1080p), API 34, with the hardware keyboard disabled.

Running on a Physical Device
Testing on a real device provides the most reliable results, especially for apps that rely heavily on DPAD navigation.
Real device command exp: adb connect 192.168.1.35

Recommended devices:
Amazon Fire TV Stick (4K / 4K Max)
Any certified Android TV

Benefits of real-device testing:
Correct and consistent focus behavior
Accurate LazyRow/LazyColumn rendering timings
Reliable performance and animations
For this project, running on a Fire TV device is strongly recommended.

Architecture Overview
The project follows a simple and clear Compose-based structure suitable for small TV apps (MVVM). This is small project, no need to use clan architecture.

Core Concepts
1.Jetpack Compose for TV
UI based entirely on Jetpack Compose for TV
Uses TvLazyColumn and TvLazyRow
Focus handled through FocusRequester and DPAD navigation

2.ViewModel for State Management
The SportsHubViewModel stores:
The last focused item index for each section
(liveIndex, upcomingIndex, replaysIndex)
The last focused section (lastSection)
A one-time restore flag to trigger focus restoration
This ensures the app can return to the exact item the user selected after navigating back from the details screen.

3.Focus Restore Logic
When navigating back:
Identify the section the user came from
Scroll vertically to that row
Scroll horizontally to the stored index
Request focus on the exact item

Key Differences on Fire TV
Focus restoration requires slightly longer delays (120–200 ms)
Fire OS aggressively recycles UI elements, causing more recompositions
DPAD events may be grouped or ignored under heavy GPU load
Rendering and focus events may not occur in the same frame

Next Steps & Possible Improvements
Add HILT for DI
Add some Add Transitions & Animations
Implement Deep Links
Add Automated Focus Tests
Add Loading, Error, and Empty States