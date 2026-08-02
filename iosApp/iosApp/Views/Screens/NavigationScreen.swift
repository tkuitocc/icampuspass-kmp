import SwiftUI

struct NavigationScreen: View {
    var body: some View {
        switch UIDevice.current.userInterfaceIdiom {
        case .pad,
            .mac:
            if #available(iOS 16.0, *) {
                @State var columnVisibility = NavigationSplitViewVisibility.all

                NavigationSplitView(columnVisibility: $columnVisibility) {
                    SandboxScreen()
                        .navigationTitle("Hello, world!")
                } detail: {
                    SandboxScreen()
                        .navigationTitle("Hello, world!")
                }
                .navigationSplitViewStyle(.balanced)
            } else {
                SwiftUI.NavigationView {
                    SandboxScreen()
                        .navigationTitle("Hello, world!")
 
                    SandboxScreen()
                        .navigationTitle("Hello, world!")
                }
                .navigationViewStyle(.columns)
            }
        default:
            if #available(iOS 18.0, *) {
                TabView() {
                    Tab("Hello, world!", systemImage: "calendar.badge.clock") {
                        SandboxScreen()
                            .navigationTitle("Hello, world!")
                    }

                    Tab("Hello, world!", systemImage: "tray.and.arrow.up.fill") {
                        SandboxScreen()
                            .navigationTitle("Hello, world!")
                    }
 
                    Tab("Hello, world!", systemImage: "person.crop.circle.fill") {
                        SandboxScreen()
                            .navigationTitle("Hello, world!")
                    }
                }
            } else {
                TabView {
                    SandboxScreen()
                        .navigationTitle("Hello, world!")
                        .tabItem {
                            Label("Hello, world!", systemImage: "list.dash")
                        }

                    SandboxScreen()
                        .tabItem {
                            Label("Hello, world!", systemImage: "list.dash")
                        }

                    SandboxScreen()
                        .tabItem {
                            Label("Hello, world!", systemImage: "list.dash")
                        }

                    SandboxScreen()
                        .tabItem {
                            Label("Hello, world!", systemImage: "list.dash")
                        }
                }
            }
        }

    }
}

struct NavigationScreen_Previews: PreviewProvider {
    static var previews: some View {
        NavigationScreen()
    }
}
