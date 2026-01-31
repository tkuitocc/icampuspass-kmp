import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct GreetingScreen: View {
    @StateViewModel var viewModel = GreetingScreenViewModel()

    @State private var showContent = false

    var body: some View {
        VStack {
            Button(
                action: {
                    withAnimation {
                        showContent = !showContent
                    }
                }
            ) {
                Text(
                    String(
                        localized: "greeting_screen_greeting_button_label",
                        defaultValue: "Click me!",
                        table: "GreetingScreen",
                        comment: "The toggle button label for greeting content on the Greeting Screen."
                    )
                )
            }
            .buttonStyle(.borderedProminent)

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                    .font(.system(size: 200))
                    .foregroundColor(.accentColor)

                    Text(
                        String(
                            localized: "greeting_screen_greeting_content_text",
                            defaultValue: "SwiftUI: \(Greeting().greet())",
                            table: "GreetingScreen",
                            comment: "The greeting content text on the Greeting Screen."
                        )
                    )
                }
                .transition(.move(edge: .top).combined(with: .opacity))
            }
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
        .padding()
    }
}

struct GreetingScreen_Previews: PreviewProvider {
    static var previews: some View {
        GreetingScreen()
    }
}
