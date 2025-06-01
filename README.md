This is a Kotlin Multiplatform project targeting Android, iOS, Web, Desktop.

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html),
[Compose Multiplatform](https://github.com/JetBrains/compose-multiplatform/#compose-multiplatform),
[Kotlin/Wasm](https://kotl.in/wasm/)…

We would appreciate your feedback on Compose/Web and Kotlin/Wasm in the public Slack channel [#compose-web](https://slack-chats.kotlinlang.org/c/compose-web).
If you face any issues, please report them on [GitHub](https://github.com/JetBrains/compose-multiplatform/issues).

You can open the web application by running the `:composeApp:wasmJsBrowserDevelopmentRun` Gradle task.

---

## 🎢 Roadmap

- [x] Add `ktlint` configuration for code style enforcement.
- [x] Integrate `Arrow Core` for exception handling.
- [x] Add a centralized logger.
- [ ] Create a separate module for reusable UI components.
- [x] Configure GitHub Actions workflow.
- [ ] Assign unique IDs to objects and enforce consistent ordering logic.
- [ ] Correct usage of visibility modifiers across the codebase.
- [ ] Introduce common @Preview annotations for reusable Composable previews.
- [ ] Implement section-wise scroll behavior on the `Detail Screen` for better navigation.
- [ ] Add inline code formatting support for syntax display on the `Detail Screen`.
- [ ] Replace the search icon on the `Topic Card` to improve visual consistency.