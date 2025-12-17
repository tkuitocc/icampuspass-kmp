// Standard imports
import SwiftUI

struct TestingScreen: View {
    @State private var showContent: Bool = false

    let localizedDateString: String = Date()
        .formatted(.dateTime.month(.wide).day().weekday(.wide))

    var body: some View {
        VStack {
            HStack(alignment: .bottom) {
                VStack(alignment: .leading) {
                    Text(verbatim: localizedDateString)
                        .font(.headline)
                        .foregroundStyle(.secondary)

                    Text(
                        String(
                            localized: "testing_screen_greeting_title",
                            defaultValue: "Testing",
                            table: "TestingScreen",
                            comment: "The title on the Testing Screen."
                        )
                    )
                        .font(.largeTitle)
                        .fontWeight(.bold)
                }

                Spacer()

                Button(
                    action: {
                    }
                ) {
                    Image(systemName: "person.crop.circle")
                        .font(.largeTitle)
                        .imageScale(.large)
                }
            }
            .frame(maxWidth: .infinity)

            Button(
                action: {
                    withAnimation {
                        showContent = !showContent
                    }
                }
            ) {
                Text(
                    String(
                        localized: "testing_screen_greeting_button_label",
                        defaultValue: "Click me!",
                        table: "TestingScreen",
                        comment: "The toggle button label for greeting content on the Testing Screen."
                    )
                )
            }
            .buttonStyle(.borderedProminent)

            if showContent {
                VStack(spacing: 16) {
                    Image(systemName: "swift")
                        .font(.system(size: 200))
                        .foregroundStyle(.tint)

                    Text(
                        String(
                            localized: "testing_screen_greeting_content_text",
                            defaultValue: "SwiftUI: Hello, world!",
                            table: "TestingScreen",
                            comment: "The greeting content text on the Testing Screen."
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

#Preview {
    TestingScreen()
}
