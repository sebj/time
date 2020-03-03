//
//  Week.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import Foundation

struct Week: Identifiable {
    let id = UUID()
    let cells: [DayCell]
}
