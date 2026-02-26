import SwiftUI

struct ClassSessionCard: View {
    var session: ClassScheduleSession

    var body: some View {
        HStack {
            Text(session.name)
        }
    }
}


#Preview {
    ClassSessionCard(
        session: ClassScheduleSession(
            id: 0,
            name: "Course name"
        )
    )
}
