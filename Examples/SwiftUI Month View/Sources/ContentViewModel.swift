//
//  ContentViewModel.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import Combine
import Time

final class ContentViewModel: ObservableObject {
    
    @Published
    private(set) var title = ""
    
    @Published
    private(set) var selectedDay: Absolute<Day>? {
        didSet {
            self.title = selectedDay?.format(year: .full, month: .fullName, day: .full) ?? ""
        }
    }
    
    lazy var gridViewModel = MonthGridViewModel(
        clock: self.clock,
        month: self.month.eraseToAnyPublisher(),
        didSelectDay: { [weak self] day in
            self?.title = day.format(year: .full, month: .fullName, day: .full)
        })
    
    private let clock: Clock
    private let month: CurrentValueSubject<Absolute<Month>, Never>
    
    init(clock: Clock = .system) {
        self.clock = clock
        self.month = .init(clock.thisMonth())
    }
    
    func onAppear() {
        self.month.send(self.clock.thisMonth())
    }
    
    func selectPreviousMonth() {
        self.month.send(self.month.value - .months(1))
    }
    
    func selectNextMonth() {
        self.month.send(self.month.value + .months(1))
    }
}
