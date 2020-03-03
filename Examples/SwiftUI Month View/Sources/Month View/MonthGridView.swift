//
//  MonthGridView.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import Combine
import SwiftUI
import Time

struct MonthGridView: View {
    @ObservedObject
    private(set) var viewModel: MonthGridViewModel
    
    private func view(for day: DayCell, size: CGFloat) -> some View {
        switch day {
        case .empty:
            return EmptyCellView(size: size).erasedToAnyView()
            
        case let .day(day):
            return DayCellView(
                day: day,
                isSelected: day == self.viewModel.selectedDay,
                isToday: self.viewModel.isToday(day),
                onTap: { self.viewModel.selectDay(day) },
                size: size)
                .erasedToAnyView()
        }
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack(alignment: .trailing, spacing: 0) {
                ForEach(self.viewModel.weeks) { week in
                    HStack(spacing: 0) {
                        ForEach(week.cells, id: \.self) { day in
                            self.view(
                                for: day,
                                size: geometry.size.width / CGFloat(self.viewModel.cellsPerWeek))
                        }
                    }
                }
            }
        }
        .aspectRatio(1, contentMode: .fit)
        .onAppear(perform: self.viewModel.onAppear)
    }
}

struct MonthGridView_Previews: PreviewProvider {
    static var previews: some View {
        MonthGridView(
            viewModel: .init(
                month: Just(Clock.system.thisMonth()).eraseToAnyPublisher(),
                didSelectDay: { _ in }))
            .previewLayout(.fixed(width: 375, height: 375))
    }
}
