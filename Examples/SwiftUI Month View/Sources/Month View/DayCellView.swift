//
//  DayCellView.swift
//
//  Created by Sebastian Jachec on 02/03/2020.
//

import SwiftUI
import Time

struct DayCellView: View {
    let day: Absolute<Day>
    let isSelected: Bool
    let isToday: Bool
    let onTap: () -> Void
    let size: CGFloat
    
    var body: some View {
        Text(String(self.day.dayOfMonth))
            .foregroundColor(isSelected ? Color(.systemBackground) : .primary)
            .font(.system(size: 16, weight: self.isSelected ? .bold : .regular))
            .onTapGesture(perform: self.onTap)
            .frame(width: size, height: self.size)
            .background(self.isSelected ? Circle().fill(Color.accentColor) : nil)
            .background(self.day.isWeekend ? Color(.systemGray5) : nil)
            .accentColor(self.isToday ? .blue : .primary)
            .accessibility(label: Text(self.day.format(date: .full)))
    }
}

// MARK: - Previews

struct DayCellView_Previews: PreviewProvider {
    static let size: CGFloat = 44
    static let onTap = {}
    
    static var previews: some View {
        Group {
            HStack {
                DayCellView(
                    day: Clock.system.yesterday(),
                    isSelected: false,
                    isToday: false,
                    onTap: onTap,
                    size: size)
                
                DayCellView(
                    day: Clock.system.yesterday(),
                    isSelected: true,
                    isToday: false,
                    onTap: onTap,
                    size: size)
            }
            .previewDisplayName("Not Today")
            
            HStack {
                DayCellView(
                    day: Clock.system.today(),
                    isSelected: false,
                    isToday: true,
                    onTap: onTap,
                    size: size)
                
                DayCellView(
                    day: Clock.system.today(),
                    isSelected: true,
                    isToday: true,
                    onTap: onTap,
                    size: size)
            }
            .previewDisplayName("Today")
        }
        .padding()
        .previewLayout(.sizeThatFits)
    }
}
