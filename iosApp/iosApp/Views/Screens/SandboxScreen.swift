import SwiftUI

struct SandboxScreen: View {
    var body: some View {
        VStack {
            Image(systemName: "calendar.badge.clock")
                .imageScale(.large)
                .foregroundStyle(.tint)

            Text("Hello, world!")
        }
        .padding()
    }
}

struct SandboxScreen_Previews: PreviewProvider {
    static var previews: some View {
        SandboxScreen()
    }
}
