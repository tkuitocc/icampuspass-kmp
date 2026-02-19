// Standard imports
import SwiftUI

// Third-party imports
import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI

// Local imports
import Shared

struct MainScreen: View {
    @StateViewModel var viewModel = MainScreenViewModel()

    var body: some View {
    }
}

#Preview {
    MainScreen()
}
