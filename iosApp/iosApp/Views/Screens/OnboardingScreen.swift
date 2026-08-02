// Standard imports
import SwiftUI

struct OnboardingScreen: View {
    var body: some View {
        if #available(iOS 18, *) {
            OnboardingScreen_iOS18()
        } else {
            OnboardingScreen_iOS15()
        }
    }
}

@available(iOS 18.0, *)
private struct OnboardingScreen_iOS18: View {
    @State private var currentSelection = 0

    init() {
        UIPageControl.appearance().currentPageIndicatorTintColor = .tintColor
        UIPageControl.appearance().pageIndicatorTintColor = .secondaryLabel
    }

    var body: some View {
        TabView(selection: $currentSelection) {
            Tab(value: 0) {
                TestingScreen()
            }
            
            Tab(value: 1) {
                TestingScreen()
            }
    
            Tab(value: 2) {
                TestingScreen()
            }
        }
        .tabViewStyle(.page)
    }
}

@available(iOS, deprecated: 18.0, renamed: "OnboardingScreen_iOS18")
struct OnboardingScreen_iOS15: View {
    @State private var currentSelection = 0

    init() {
        UIPageControl.appearance().currentPageIndicatorTintColor = .tintColor
        UIPageControl.appearance().pageIndicatorTintColor = .secondaryLabel
    }

    var body: some View {
        TabView(selection: $currentSelection) {
            TestingScreen()
                .tag(0)

            TestingScreen()
                .tag(1)

            TestingScreen()
                .tag(2)
        }
        .tabViewStyle(.page)
    }
}

#Preview {
    OnboardingScreen()
}
