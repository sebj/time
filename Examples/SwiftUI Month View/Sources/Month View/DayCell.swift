//
//  DayCell.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import Foundation
import Time

enum DayCell: Hashable {
    case empty(UUID = .init())
    case day(Absolute<Day>)
}
