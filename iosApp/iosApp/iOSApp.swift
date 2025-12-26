import Shared
import SwiftUI

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            NavigationScreen()
        }
    }
}
