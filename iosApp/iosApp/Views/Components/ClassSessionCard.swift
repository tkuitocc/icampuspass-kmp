import SwiftUI

struct ClassSessionCard: View {
    var session: ClassScheduleSession

    var body: some View {
        HStack {
            Text(session.name)
        }
    }
}


struct ClassSessionCard_Previews: PreviewProvider {
    static var previews: some View {
        ClassSessionCard(
            session: ClassScheduleSession(
                id: 0,
                name: "Course name"
            )
        )
    }
}
