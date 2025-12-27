import KMPNativeCoroutinesAsync
import KMPObservableViewModelSwiftUI
import Shared
import SwiftUI

struct MainScreen: View {
    @StateViewModel var viewModel = MainScreenViewModel()

    var body: some View {
    }
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}

