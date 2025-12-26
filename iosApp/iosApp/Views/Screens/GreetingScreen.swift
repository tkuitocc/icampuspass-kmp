import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct GreetingScreen: View {
    @StateViewModel var viewModel = GreetingScreenViewModel(
        userRepository: KoinDependencies().userRepository
    )

    @State private var showContent = false

    var body: some View {
        VStack {
            Button("Click me!") {
                withAnimation {
                    showContent = !showContent
                }
            }

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                    .font(.system(size: 200))
                    .foregroundColor(.accentColor)

                    Text("SwiftUI: \(viewModel.getGreeting().greet())")
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
