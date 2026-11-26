import SwiftUI

struct NavigationView: View {
    var body: some View {
        if #available(iOS 16.0, *) {
            @State var columnVisibility = NavigationSplitViewVisibility.all

            NavigationSplitView(columnVisibility: $columnVisibility) {
                Text("Hello, world!")
                    .navigationTitle("Hello, world!")
            } detail: {
                Text("Hello, world!")
                    .navigationTitle("Hello, world!")
            }
            .navigationSplitViewStyle(.balanced)
        } else {
            // Fallback on earlier versions
        }
    }
}

struct NavigationView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationView()
    }
}
