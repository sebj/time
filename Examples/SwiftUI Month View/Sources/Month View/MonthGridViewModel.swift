//
//  MonthGridViewModel.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import Combine
import Time

final class MonthGridViewModel: ObservableObject {
    @Published
    private(set) var weeks = [Week]()
    
    @Published
    private(set) var cellsPerWeek = 1
    
    @Published
    private(set) var selectedDay: Absolute<Day>?
    
    private let clock: Clock
    private let monthPublisher: AnyPublisher<Absolute<Month>, Never>
    private let didSelectDay: (Absolute<Day>) -> Void
    
    private var cancellable: AnyCancellable?
    
    init(
        clock: Clock = .system,
        month: AnyPublisher<Absolute<Month>, Never>,
        didSelectDay: @escaping (Absolute<Day>) -> Void)
    {
        self.clock = clock
        self.monthPublisher = month
        self.didSelectDay = didSelectDay
    }
    
    func onAppear() {
        self.cancellable = self.monthPublisher.sink { [weak self] month in
            guard let self = self else {
                return
            }
            
            self.populateWeeks(month: month)
            
            if month == self.clock.thisMonth() {
                self.selectDay(self.clock.today())
            } else {
                self.selectDay(month.firstDay())
            }
        }
    }
    
    func isToday(_ day: Absolute<Day>) -> Bool {
        day == self.clock.today()
    }
    
    func selectDay(_ day: Absolute<Day>) {
        self.didSelectDay(day)
        self.selectedDay = day
    }
    
    private func populateWeeks(month: Absolute<Month>) {
        var weeks = [Week]()
        var cells = [DayCell]()
        for day in month.days() {
            if day.dayOfWeek == day.calendar.firstWeekday {
                weeks.append(Week(cells: cells))
                cells = [.day(day)]
            } else {
                cells.append(.day(day))
            }
        }
        
        if
            case let .day(lastDay) = cells.last,
            !cells.isEmpty
        {
            let emptyDays = AbsoluteValueSequence(
                start: lastDay + .days(1),
                stride: .days(1),
                while: { day in
                    day.dayOfWeek != day.calendar.firstWeekday
                })
                .map { _ in DayCell.empty() }
            
            cells.append(contentsOf: emptyDays)
            weeks.append(Week(cells: cells))
        }
        
        self.weeks = weeks
        self.cellsPerWeek = weeks.map { $0.cells.count }.max() ?? 1
    }
}
